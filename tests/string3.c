#ifdef GCC /* GCC Code*/

#define STRING_SIZE 128

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

char * cat( char *s, char *t ) {
  char *p;
  p = (char *) malloc(STRING_SIZE);
  strncpy( p, s , STRING_SIZE );
  strncat( p, t , STRING_SIZE );
  return p;
}

char * invert( char *s ) {
  static char buf[STRING_SIZE];
  char *b;
  int i;
  b = strdup(s);
  for ( i = 0; i < strlen(s); i++ )
    b[i] = s[strlen(s)-1-i];
  strncpy( buf, b, STRING_SIZE-1 );
  free(b);
  buf[STRING_SIZE-1] = '\0';
  return buf;
}

int main() {
  char *s = "hello";
  char *t = "world";
  printf(cat(cat(s,t),invert(cat(t,s))));
  printf(invert(invert(invert(invert(invert(invert(invert(invert(t)))))))));
  return 0;
}

#else /* code compiler */

#include "../lib/lib.h"

string sub( string s, int i, int j ) {
  int k;
  string r;
  r = "";
  for ( k = i; k <= j; k = k+1 )
    r = r + get_char_at( s, k );
  return r;
}

string invert( string s ) {
  if ( s == "" )
    return "";
  return invert(sub(s,1,strlen(s)-1)) + get_char_at(s,0);
}

int main() {
  string s;
  string t;
  s = "hello";
  t = "world";
  printf( s+t+invert(t+s) );
  printf(invert(invert(invert(invert(invert(invert(invert(invert(t)))))))));
  return 0;
}

#endif
