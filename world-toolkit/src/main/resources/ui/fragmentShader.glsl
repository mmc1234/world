#version 130

flat in int triangleID;
out vec4 fragColor;

// uniform sampler2D dataSampler

void main() {
  if(color == 0) { // 0 == false
    gl_FragDepth = 1;
  }
  fragColor = vec4(0, 1, 0, 1);
}