/*
 * Lexer Rules
 */

grammar Canon;

IDENTIFIER : (Letter | SpecialChar) (LetterOrDigit | SpecialChar)*;
HEADLINE   : '= ' Character+;
ITEM       : '. ' Character+;
BOLD       : '*' Character+ '*';
CODE       : '----' Character+ '----';
IMAGE      : 'image::' Character+;
STARTTAG   : '// tag::' IDENTIFIER '[]';
ENDTAG     : '// end::' IDENTIFIER '[]';
INTENT     : '// intent::' Character+;

WS         : [ \t\r\n\u000C]+ -> skip;
LC         : '//' ~[\r\n]*    -> skip;

fragment Letter          : [a-zA-Z];
fragment LetterOrDigit   : [a-zA-Z0-9_];
fragment SpecialChar     : ('-' | '.' | '\'' | '\u00DF' | '\u0022' | '\u00DC' | '?' | '!' | '<' | '>' | '/' | 'ü');
fragment Character       : ~('\r' | '\n');

/*
 * Parser Rules
 */
compilation  : (tag | intent | headline | image | items | bold | code | text)* ;
startTag     : STARTTAG;
endTag       : ENDTAG;
intent       : INTENT;
tag          : startTag (intent  | headline | image | items | bold | code | text)* endTag;
headline     : HEADLINE;
image        : IMAGE;
items        : item+;
item         : ITEM;
bold         : BOLD;
code         : '----' text '----';
text         : (IDENTIFIER | ' ')+;
