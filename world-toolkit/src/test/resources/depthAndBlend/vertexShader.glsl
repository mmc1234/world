#version 130

in vec3 inVertex;
in float inColor;
out float color;

void main()
{
  gl_Position = vec4(inVertex, 1);
  color = inColor;
}