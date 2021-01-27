#version 130

in vec4 inVertex;
in int inIndex;

void main()
{
  gl_Position = vec4(inVertex.x, inVertex.y, inIndex/10000, 1);
}