#version 130

flat in vec4 clip;
out vec4 fragColor;

void main() {
  if(gl_FragCoord.x >= clip.r && gl_FragCoord.y >= clip.g && gl_FragCoord.x <= clip.b && gl_FragCoord.y <= clip.a) {
    fragColor = vec4(0, 1, 0, 1);
  }  else {
    fragColor = vec4(0, 0, 0, 0);
  }
}