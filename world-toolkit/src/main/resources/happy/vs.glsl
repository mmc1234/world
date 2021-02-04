#version 130

in vec4 inVertex;
in int inTexBit;

flat out int texBit;
out vec2 texCoord;

void main() {
  gl_Position = vec4(inVertex.x, inVertex.y, 0, 1);
  texCoord = vec2(inVertex.z, inVertex.w);
  texBit = inTexBit;
}