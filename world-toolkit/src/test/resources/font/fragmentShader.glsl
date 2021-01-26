#version 130

in vec2 tex;
out vec4 fragColor;

uniform sampler2D sam;

void main() {
  fragColor = texture(sam, tex);
}