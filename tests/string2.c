int printd( int a );
string u;

int main() {
  string s;
  string t;

  s = "hello";
  t = "helll";
  u = "hellp";

  if (s == t) printd(1); else printd(0);
  if ("abc" == "abc") printd(1); else printd(0);
  if (t == s) printd(1); else printd(0);
  if (t+"abc" == u+"ab") printd(1); else printd(0);
  if (s+t+u == s+t+u) printd(1); else printd(0);

  if (s != t) printd(1); else printd(0);
  if ("abc" != "abc") printd(1); else printd(0);
  if (t != s) printd(1); else printd(0);
  if (t+"abc" != u+"ab") printd(1); else printd(0);
  if (s+t+u != s+t+u) printd(1); else printd(0);

  return 0;
}


