{
  description = "Minimal example of building Kotlin with Gradle and Nix";

  inputs = { 
    nixpkgs.url = "github:nixos/nixpkgs?ref=nixos-unstable";
    flake-utils.url = "github:numtide/flake-utils";
    build-gradle-application.url = "github:raphiz/buildGradleApplication";
    systems.url = "github:nix-systems/triplet";
    flake-utils.inputs.systems.follows = "systems";
  };
  outputs = { self, systems, nixpkgs, build-gradle-application, flake-utils, ... }:
    flake-utils.lib.eachDefaultSystem (system:
      let
        pkgs = import nixpkgs { inherit system; overlays = [ build-gradle-application.overlays.default ]; };
        molpic = pkgs.buildGradleApplication {
          pname = "anodyne-molpic";
          version = "0.1.0";
          src = ./.;
        };
      in {
        hydraJobs = {
          inherit (self)
            packages
            ;
        };
        devShells.default = pkgs.mkShell {
          packages = [
            molpic
            pkgs.imagemagick
            pkgs.libsixel
          ];
        };
        packages.default = molpic;
      });
}
