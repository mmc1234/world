#version 130

in vec4 outColor;
out vec4 fragColor;

float ln(float n) {
  return log(n)/log(2.718281828459);
}

void main() {
  fragColor = vec4(-ln(1-outColor.a), 0, 0, 1);
}