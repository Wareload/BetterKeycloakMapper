
{ pkgs ? import <nixpkgs> {} }:

pkgs.mkShell {
  buildInputs = with pkgs; [
    maven
    jdk21
    kotlin
    git
  ];
}
