int printd(int i);
int printf(string s);
string get_char_at(string s, int i);
string put_char_at(string s, int i, string p);

string tab;
int N;

int main() {
  int i,j;
  int N;

  N = 100;

  tab = "0";
  for ( i = 1 ; i < N ; i = i+1 )
    tab = tab + '1';
  for ( i = 2 ; i < N ; i = i+1 )
    for ( j = i+i; j < N ; j = j+i ) 
      tab = put_char_at( tab, j, '0' );
  
  for ( i = 0 ; i < N ; i = i+1 )
    if ( get_char_at( tab, i ) == '1' ) {
      printd(i);
      printf(" ");
    }
  printf("\n");
  return 0;
}
