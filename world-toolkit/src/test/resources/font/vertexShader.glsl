#version 130

in vec2 inPos;
in vec2 inTex;
out vec2 tex;

void main()
{
  gl_Position = vec4(inPos, 0, 1);
  tex = inTex;
}