#version 130

in float color;
out vec4 fragColor;


void main() {
  if(color == 0) { // 0 == false
    gl_FragDepth = 1;
  }
  fragColor = vec4(0, 1, 0, 1);
}