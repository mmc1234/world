#version 130

in vec2 vertexCoord;
in vec4 color;
out vec4 outColor;

void main() {
  gl_Position = vec4(vertexCoord, 0, 1);
  outColor = color;
}