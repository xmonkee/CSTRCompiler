string cat (string a, string b);
extern int printf( string s );

string u;

int main() {
  string s;
  string t;

  s = "hello";
  t = " world\n";
  u = "bye";
  printf("hello");
  printf(cat("hello","world\n"));
  printf(cat(s,"world\n"));
  printf(cat("hello",t));
  printf(cat(s,t));
  printf(cat((cat(s,u)),t));
  printf(cat(s,(cat(u,t))));
  printf(cat((cat(s,s)),(cat(t,t))));
  printf(cat(cat(s,u),t));
  return 0;
}

string cat(string a, string b) {
    return a + b;
}
