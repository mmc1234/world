#version 130

in vec3 inPos;
in vec3 inBmp;
in int inColor;
in int inLight;
in int inLocalPos;

out vec3 bitmap;
out vec3 color;
out float light;

void main() {
  gl_Position = vec4(inPos, 1) * vec3(inLocalPos & 0xff, inLocalPos>>8 & 0xff, inLocalPos >> 16 & 0xff);
  bitmap = inBmp;
  color = vec4(inColor & 0xff, inColor >> 8 & 0xff, inColor >> 16 & 0xff);
  light = light/16.0f;
}