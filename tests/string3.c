string get_char_at(string s, int i);
int strlen(string s);
int printf(string s);


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

