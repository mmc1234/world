#version 130

out vec4 fragColor;

void main() {
  fragColor = vec4(gl_FragCoord.x/800, gl_FragCoord.y/600, 0, 1);
}