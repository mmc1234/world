#version 130

in vec2 inVertex;
// in vec3 inBitmap;
// in int inColor;
flat out int triangleID;
// out vec4 color;

void main()
{
  gl_Position = vec4(inVertex, 0, 1);
  // color = int to vec4
  triangleID = gl_VertexID/3;
}