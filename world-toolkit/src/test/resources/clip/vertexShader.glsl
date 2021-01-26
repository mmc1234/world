#version 130

in vec2 pos;
in vec4 inClip;
flat out vec4 clip;

void main()
{
  gl_Position = vec4(pos/12, 0, 1);
  clip = inClip;
}