//@Grab(group='org.openscience.cdk', module='cdk-bundle', version='2.11')
//@Grab(group='org.openscience.cdk', module='cdk-depict', version='2.11')

import java.util.ArrayList
import java.awt.Font

import groovy.lang.GroovyObject
import groovy.cli.commons.CliBuilder
import groovy.json.JsonOutput

import org.openscience.cdk.depict.DepictionGenerator
import org.openscience.cdk.renderer.generators.standard.StandardGenerator
import org.openscience.cdk.smiles.SmilesParser
import org.openscience.cdk.DefaultChemObjectBuilder
import org.openscience.cdk.layout.StructureDiagramGenerator

import org.openscience.cdk.interfaces.IAtom
import org.openscience.cdk.interfaces.IBond
import org.openscience.cdk.interfaces.IAtomContainer

import org.openscience.cdk.isomorphism.VentoFoggia
import org.openscience.cdk.isomorphism.AtomMatcher
import org.openscience.cdk.isomorphism.BondMatcher
import org.openscience.cdk.isomorphism.Pattern

class Main {
static void main(String [] args) {
System.setProperty('java.awt.headless', 'true')

def cli = new CliBuilder(usage: 'groovy Main.groovy [options]')
cli.h(longOpt:  'help', 'Show this help message')
cli.m(longOpt:  'molecule', args: 1, 'Molecule SMILES')
cli.s(longOpt:  'salt', args: 1, 'Salt SMILES')
cli.am(longOpt: 'amine-count', args: 1, 'Amine count')
cli.ac(longOpt: 'acid-count', args: 1, 'Salt count')
cli.r(longOpt:  'reaction', args: 1, 'Reaction SMILES')
cli.o(longOpt:  'output', args: 1, 'Output file')
cli.si(longOpt: 'sixel', 'Display as sixel')
cli.d(longOpt:  'debug-file', args: 1, 'Debug file')
cli.v(longOpt:  'verbose', 'Enable verbose mode')

def options = cli.parse(args)
if (!options) {
	println "failed to parse options"
	return
}
if (options.h) {
	cli.usage()
	return
}
if (!options.m && !options.r) {
	println "no molecule or reaction specified"
	return
}
if (options.m && options.r) {
	println "both molecule and reaction specified"
	return
}
if (options.s && options.r) {
	println "both salt and reaction specified"
	return
}

def amine_count = options.am ? options.am.toInteger() : 1
def acid_count = options.ac ? options.ac.toInteger() : 1

if (options.v) {
	if (options.m) println "Molecule: ${options.m}"
	if (options.s) println "Salt: ${options.s}"
	if (amine_count) println "Salt Amine Count: ${amine_count}"
	if (acid_count) println "Salt Acid Count: ${acid_count}"
	if (options.r) println "Reaction: ${options.r}"
	// if (options.o) println "Output: ${options.o ?: 'molecule.svg'}"
	// println "Verbose: ${options.v}"
}

def builder = DefaultChemObjectBuilder.instance
def sp = new SmilesParser(builder)

def target = null
def salt = null

def svg = ""

if (options.m) target = sp.parseSmiles(options.m)
if (options.s) salt = sp.parseSmiles(options.s)
if (options.r) target = sp.parseReactionSmiles(options.r)

def sdg = new StructureDiagramGenerator()

def substitutions = Substitutions.list

def classes = []

for (sub in substitutions) {
	sub.mol = sp.parseSmiles(sub.smiles)
	def bm = BondMatcher.forOrder()
	if (sub.bany)
		bm = BondMatcher.forAny()

	sub.pattern = VentoFoggia.findSubstructure(sub.mol, AtomMatcher.forElement(), bm)
}

for (sub in substitutions) {
	if (sub.pattern.match(target).length > 0 && sub.fame == null) {
		if (sub.its) {
			//if (sub.nself == null)
			//	sub.nself = true
			IAtomContainer lsubst = null
			Pattern lpattern = null
			for (it in sub.its) {
				for (ssub in substitutions) {
					if (it == ssub.name || it == ssub.fame) {
						if (lsubst == null)
							sdg.generateCoordinates(ssub.mol)
						else
							sdg.generateAlignedCoordinates(ssub.mol, lsubst, lpattern)
						if (ssub.rot != null)
							Align.rotate(ssub.mol, ssub.rot)

						if (ssub.flipx)
							Align.flipx(ssub.mol)
						if (ssub.flipy)
							Align.flipy(ssub.mol)

						lsubst = ssub.mol
						lpattern = ssub.pattern
						break
					}
				}
			}
			if (lsubst == null) {
				if (sub.nself == true)
					sdg.generateCoordinates(target)
				else
					sdg.generateCoordinates(sub.mol)
			} else {
				if (sub.nself == true) {
					if (sub.ntarg != true) sdg.generateAlignedCoordinates(target, lsubst, lpattern)
				} else {
					sdg.generateAlignedCoordinates(sub.mol, lsubst, lpattern)
				}
			}
		} else {
			if (sub.lame == null) {
				if (sub.nself == true) {
					if (sub.ntarg != true) sdg.generateCoordinates(target)
				} else {
					sdg.generateCoordinates(sub.mol)
				}
			}
		}
		if (sub.lame == null) {
			if (sub.nself == true) {
				if (sub.rot != null)
					Align.rotate(target, sub.rot)
				if (sub.flipx == true)
					Align.flipx(target)
				if (sub.flipy == true)
					Align.flipy(target)
			} else {
				if (sub.rot != null)
					Align.rotate(sub.mol, sub.rot)
				if (sub.flipx == true)
					Align.flipx(sub.mol)
				if (sub.flipy == true)
					Align.flipy(sub.mol)

				sdg.generateAlignedCoordinates(target, sub.mol, sub.pattern);
			}

			if (sub.amph)
				Align.amphetamine_fix(target)
		}

		if (sub.name) {
			classes.add(sub.name)
			break
		}
		if (sub.lame) {
			classes.add(sub.lame)
		}
	}
}

classes.unique()

if (classes.size() > 0)
	println "Classes: ${classes}"

def depict = new DepictionGenerator(new Font("monospace", Font.PLAIN, 18))
	.withAtomColors()
	.withZoom(3.0)
	.withPadding(13.5)
	.withParam(StandardGenerator.StrokeRatio.class, 1.4d)

if (options.m && options.s) {
	def molecules = new ArrayList<IAtomContainer>()
	for (int ami = 0; ami < amine_count; ami++) {
		molecules.add(target)
	}
	for (int cmi = 0; cmi < acid_count; cmi++) {
		molecules.add(salt)
	}
	svg = depict.depict(molecules).toSvgStr()
} else {
	svg = depict.depict(target).toSvgStr()
}

if (!svg) return

svg = Highlight.modifySVG(svg).replaceAll(/>\s+</, "><")

new File(options.o ?: "molecule.svg").withWriter("UTF-8") { writer ->
	writer.write(svg)
}

if (options.si) {
	def svgFile = options.o ?: "molecule.svg"
	def magick = ["magick", "convert"].find { bin ->
		try { ["which", bin].execute().waitFor() == 0 } catch (ignored) { false }
	}
	if (!magick) {
		System.err.println "sixel: ImageMagick (magick/convert) not found on PATH"
	} else if (["which", "img2sixel"].execute().waitFor() != 0) {
		System.err.println "sixel: img2sixel (libsixel) not found on PATH"
	} else {
		def png = File.createTempFile("molpic", ".png")
		try {
			// SVG -> PNG via ImageMagick, cropped to the occupied area, capped at 500x250 (aspect preserved)
			def convert = [magick, "-background", "white", svgFile, "-flatten", "-fuzz", "1%", "-trim", "+repage", "-resize", "500x250>", png.absolutePath]
			def cp = convert.execute()
			def cerr = new StringBuffer()
			cp.consumeProcessErrorStream(cerr)
			cp.waitFor()
			if (cp.exitValue() != 0) {
				System.err.println "sixel: SVG->PNG conversion failed:\n${cerr}"
			} else {
				// PNG -> sixel, streamed to the terminal
				def sp2 = ["img2sixel", png.absolutePath].execute()
				sp2.waitForProcessOutput(System.out, System.err)
			}
		} finally {
			png.delete()
		}
	}
}

def json_data = [
	svg: svg,
	classes: classes
]
def json = JsonOutput.prettyPrint(JsonOutput.toJson(json_data))

new File(options.d ?: "molecule.json").withWriter("UTF-8") { writer ->
	writer.write(json)
}

}
}
