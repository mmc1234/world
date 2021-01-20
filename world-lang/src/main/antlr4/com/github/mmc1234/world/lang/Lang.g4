grammar Lang;

lang:
	(pkg|kv)*
	;
pkg
	:'(' ID ')'
	;
kv
  :ID ('.' ID)* '=' (var|string)+
  ;

string
	:'"' (CHAR)+ '"'
	;

var
	:'{' ID ('.' ID)* '}'
	;

ID
  :[a-zA-Z_][a-zA-Z_0-9]*
  ;


CHAR
  :~["]|('\\' '"')
  ;

WS
  :[ \t\r\n] + -> skip
  ;