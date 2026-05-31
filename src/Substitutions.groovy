class Substitutions {
	static final List list = [
		//[fame: "morp",                             smiles: "C1C=CC2=C(C=1)CCCC2"],
		[fame: "morp",                             smiles: "C2C=CC1CCC3=C(C1C2)C=CC=C3"],
		//[name: "morphinan",                        smiles: "C=1C=CC2=C(C1)CC3NCCC24CCCCC34"],
		[name: "morphinan",                        smiles: "C2C=CC1C3CC4=C(C1(C2)CCN3C)C=CC=C4", its: new String[] { "morp" }, nself: true, ntarg: true, bany: false],

		[lame: "3,4-methylenedioxyphenethylamine", smiles: "C1OC2=C(O1)C=C(C=C2)CCN"],
		[lame: "cathinone",                        smiles: "CC(C(=O)C1=CC=CC=C1)N"],

		[name: "thiobarbiturate",                  smiles: "O=C1NC(=S)NC(=O)C1", rot: 60, flipx: true],
		[name: "barbiturate",                      smiles: "C1(C(=O)NC(=O)NC1=O)", rot: -60, flipx: true],

		[fame: "indop",                            smiles: "C1CC2=CC=CC=C2CC1N", bany: true ],
		[name: "lysergamide",                      smiles: "NC(=O)C1CN(C2CC3=CNC4=CC=CC(=C34)C2=C1)C", its: new String[] { "indop" }, nself: true, flipx: true, flipy: true],
		[name: "ergoline",                         smiles: "C1CC2C(CC3=CNC4=CC=CC2=C34)NC1", its: new String[] { "indop" }, bany: true, nself: true, flipx: true, flipy: true],

		[name: "β-carboline",                      smiles: "C1=CC=C2C(=C1)C3=C(N2)C=NC=C3", bany: true],
		[name: "hexahydroazepinoindole",           smiles: "C1CNCCC2=C1C3=CC=CC=C3N2", rot: -18, bany: true, flipx: true, flipy: true],
		[name: "α-alkyltryptamine",                smiles: "CC(CC1=CNC2=CC=CC=C21)N", its: new String[] { "tryptamine" }, bany: true, amph: true],
		[name: "β-alkyltryptamine",                smiles: "CC(CN)C1=CNC2=CC=CC=C21", its: new String[] { "tryptamine" }, bany: true],
		[name: "tryptamine",                       smiles: "C1=CC=C2C(=C1)C(=CN2)CCN", rot: -42, bany: true],


		[name: "naphthylaminopropane",             smiles: "CC(CC1=CC2=CC=CC=C2C=C1)N", amph: true],

		//[fame: "phen",                             smiles: "OCC(C1CCCCN1)C2=CC=CC=C2", its: new String[] { "2-benzylpiperidine" }, amph: false, nself: true],
		[fame: "phen",                             smiles: "OCC(C1CCCCN1)C=2C=CC=CC2", its: new String[] { "2-benzylpiperidine" }, amph: false, nself: true],
		[fame: "naphphen",                         smiles: "COCC(C1CCCCN1)C2=CC3=CC=CC=C3C=C2", its: new String[] { "2-benzylpiperidine" }, amph: false, nself: true],
		[name: "phenidate",                        smiles: "OC(=O)C(C1CCCCN1)C2=CC3=CC=CC=C3C=C2", its: new String[] { "2-benzylpiperidine", "naphphen"}, amph: false, nself: true],
		//[name: "phenidate",                        smiles: "OC(=O)C(C1CCCCN1)C2=CC=CC=C2", its: new String[] { "2-benzylpiperidine", "phen"}, amph: false, nself: true],
		[name: "phenidate",                        smiles: "OC(=O)C(C1CCCCN1)C=2C=CC=CC2", its: new String[] { "2-benzylpiperidine", "phen"}, amph: false, nself: true],

		[name: "2-benzylpyrrolidine",              smiles: "C1CNC(C1)CC2=CC=CC=C2", its: new String[] { "phenethylamine", "amphetamine" }, amph: false],
		//[name: "2-benzylpiperidine",               smiles: "C1CCNC(C1)CC2=CC=CC=C2", its: new String[] { "phenethylamine", "amphetamine" }, amph: false, flipy: true],
		[name: "2-benzylpiperidine",               smiles: "C(C1CCCCN1)C=2C=CC=CC2", its: new String[] { "sphenethylamine", "amphetamine" }, amph: false, flipy: true],
		[name: "2-benzylpiperazine",               smiles: "N1CCNC(C1)CC2=CC=CC=C2", its: new String[] { "phenethylamine", "amphetamine" }, amph: false, flipy: true],

		[name: "2-phenylmorpholine",               smiles: "CC1C(OCCN1)C2=CC=CC=C2", its: new String[] { "phenethylamine", "amphetamine" }, amph: true, rot: -60, flipy: true],
		[name: "2-phenylmorpholine",               smiles: "C1COC(CN1)C2=CC=CC=C2", its: new String[] { "phenethylamine" } ],

		[name: "3-benzylmorpholine",               smiles: "C1COCC(N1)CC2=CC=CC=C2", its: new String[] { "phenethylamine", "amphetamine" }, amph: false, flipx: true],

		[fame: "methylphenethylamine",             smiles: "C1=CC=C(C=C1)CCNC", flipy: true, bany: false],
		[fame: "methamphetamine",                  smiles: "CC(CC1=CC=CC=C1)NC"],
		[fame: "sphenethylamine",                  smiles: "C(CC1=CC=CC=C1)N", bany: true, flipy: true],

		[name: "1,2-diarylethylamine",             smiles: "C1=CC=C(C=C1)CC(C2=CC=CC=C2)NC" , its: new String[] { "phenethylamine", "methamphetamine" }, amph: false, nself: true],
		[name: "1,2-diarylethylamine",             smiles: "C1=CC=C(C=C1)CC(C2=CC=CC=C2)N" , its: new String[] { "phenethylamine" }, amph: false],

		[name: "phenethylpiperidine",              smiles: "C1=CC=C(C=C1)CCN2CCCCC2", its: new String[] { "phenethylamine" }],

		[name: "piperidinophenone",                smiles: "CC(C(=O)C1=CC=CC=C1)N2CCCCC2", its: new String[] { "phenethylamine" }, amph: true],
		[name: "pyrrolidinophenone",               smiles: "CC(C(=O)C1=CC=CC=C1)N2CCCC2", its: new String[] { "phenethylamine" }, amph: true],

		[fame: "metamph",                          smiles: "CC=(CCCN)C"],
		[fame: "oramph",                           smiles: "C=C(CCN)C"],

		[name: "cathinone",                        smiles: "CC1=CC=CC=C1C(=O)C(C)NC", flipx: true, its: new String[] { "oramph", "methylphenethylamine" }, amph: true],
		[name: "cathinone",                        smiles: "CC1=CC(=CC=C1)C(=O)C(C)NC", its: new String[] { "metamph" }, amph: true],

		[name: "cathinone",                        smiles: "CC1=CC=CC=C1C(=O)C(C)N", flipx: true, its: new String[] { "oramph", "phenethylamine" }, amph: true],
		[name: "cathinone",                        smiles: "CC1=CC(=CC=C1)C(=O)C(C)N", its: new String[] { "metamph", "sphenethylamine"}, amph: true],

		[name: "amphetamine",                      smiles: "CC1=CC=CC=C1CC(C)N", flipx: true, its: new String[] { "oramph", "phenethylamine" }, amph: true],
		[name: "amphetamine",                      smiles: "CC1=CC(=CC=C1)CC(C)N", its: new String[] { "metamph", "sphenethylamine"}, amph: true],

		[name: "cathinone",                        smiles: "CC(C(=O)C1=CC=CC=C1)NC", its: new String[] { "methylphenethylamine" }, amph: true],
		[name: "cathinone",                        smiles: "CC(C(=O)C1=CC=CC=C1)N", its: new String[] { "phenethylamine" }, amph: true],

		[fame: "erp",                              smiles: "C-C-C-N"],
		[fame: "tsph",                             smiles: "C(CC1=CC=CS1)N"],
		[name: "thiopropamine",                    smiles: "CC(CC1=CC=CS1)N", its: new String[] { "erp", "tsph" }, flipx: true, amph: true],

		[name: "1-aminoindane",                    smiles: "C1CC2=CC=CC=C2C1N", flipx: true],
		[name: "1-aminoindane",                    smiles: "C1=CC2=C(CCC2N)C=C1"],
		[name: "2-aminoindane",                    smiles: "C1C(CC2=CC=CC=C21)N", bany: true ],

		//[name: "phenylethanolamine",               smiles: "CC(CC1=CC=CC=C1)N", its: new String[] { "phenethylamine" }, amph: true],
		[name: "phentermine",                      smiles: "CC(C)(CC1=CC=CC=C1)N", its: new String[] { "phenethylamine" }],

		[name: "amphetamine",                      smiles: "CC(CC1=CC=CC=C1)N", nself: true, its: new String[] { "phenethylamine" }, amph: true],

		[name: "phenylethanolamine",               smiles: "C1=CC=C(C=C1)C(CN)O", its: new String[] { "phenethylamine" }],

		[name: "phenylpropylamine",                smiles: "C1=CC=C(C=C1)CCCN", flipy: true],
		[name: "phenethylamine",                   smiles: "C1=CC=C(C=C1)CCN", flipy: true],

		[fame: "pipcrp",                           smiles: "C1CCC(CC1)(C)N3CCCCC3", rot: 100, bany: true],
		[name: "arylcyclohexylpiperidine",         smiles: "C1CCC(CC1)(C2=CC=CC=C2)N3CCCCC3", its: new String[] { "pipcrp" }, nself: true, flipy: true],

		[fame: "mopcrp",                           smiles: "C1CCC(CC1)(C)N3CCOCC3", rot: 100, bany: true],
		[name: "arylcyclohexylmorpholine",         smiles: "C1CCC(CC1)(C2=CC=CC=C2)N3CCOCC3", its: new String[] { "mopcrp" }, nself: true, flipy: true],


		[fame: "pyrcrp",                           smiles: "C1CCC(CC1)(C)N3CCCC3", rot: 100, bany: true, flipx: true],
		[name: "arylcyclohexylpyrrolidine",        smiles: "C1CCC(CC1)(C2=CC=CC=C2)N3CCCC3", its: new String[] { "pyrcrp" }, nself: true],

		[fame: "oxcrp",                            smiles: "O=C1CCCCC1(N)C", rot: 60, bany: true],
		[name: "arylcyclohexylamine",              smiles: "NC1(CCCCC1=O)C2=CC=CC=C2", its: new String[] { "oxcrp" }, flipx: true, flipy: true, bany: true, nself: true],

		[fame: "crp",                              smiles: "C1CCCCC1(N)C", rot: 60, bany: true],
		[name: "arylcyclohexylamine",              smiles: "C1CCC(CC1)(C2=CC=CC=C2)N", its: new String[] { "crp" }, bany: true, nself: true],

		[name: "1-benzylpiperazine",               smiles: "C1CN(CCN1)CC2=CC=CC=C2", its: new String[] { "benzylamine" }],
		[name: "benzylamine",                      smiles: "C1=CC=C(C=C1)CN", flipy: true],

		[name: "phenylpiperazine",                 smiles: "C1CN(CCN1)C2=CC=CC=C2", its: new String[] { "aniline" }],
		[name: "aniline",                          smiles: "C1=CC=C(C=C1)N", flipx: true, flipy: true],

		[name: "cyclohexylamine",                  smiles: "C1CCC(CC1)N", flipx: true, flipy: true],

		[name: "phenol",                           smiles: "C1=CC=C(C=C1)O", rot: 60],
		[fame: "gabaol",                           smiles: "OCCCCN"],
		[name: "gabapentinoid",                    smiles: "C(CC(=O)O)CN", its: new String[] { "gabaol"}, flipx: true],
	]
}
