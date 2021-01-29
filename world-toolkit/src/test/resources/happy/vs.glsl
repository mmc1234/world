#version 130

in vec4 inVertex;
in int texture;

void main() {
  gl_Position = vec4(inVertex.x, inVertex.y, 0, 1);
  if(texture == -1) {
    gl_Position = vec4(inVertex.x, inVertex.y, 0, 1);
  }
}