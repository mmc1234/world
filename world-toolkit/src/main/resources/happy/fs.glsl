#version 130

in vec2 texCoord;
flat in int texBit;

out vec4 fragColor;

uniform sampler2D[14] sams;

void main() {
  fragColor = texture(sams[texBit], texCoord);
}