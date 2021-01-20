grammar Shader;

//parse
shader:line*;
line:ID* ';';

ID:[a-zA-Z_][a-zA-Z_0-9]*;

EOL
  :[\r\n] + -> skip
  ;
  
WS
  :[ \t] + -> skip
  ;