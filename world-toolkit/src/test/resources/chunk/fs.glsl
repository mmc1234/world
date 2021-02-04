#version 130

in vec3 bitmap;
in vec3 color;
in float light;

out vec4 fragColor;

uniform sampler2D sam1;

void main() {
  fragColor = color*light*texture(sam1, bitmap.x bitmap.y);
}