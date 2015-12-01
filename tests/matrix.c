int printd(int i);
vec vec_new(int len);
int vec_get(vec v, int idx);
int vec_set(vec v, int idx, int val);

int main(){
    vec a;
    a = vec_new(10);
    vec_set(a, 0,10);
    printd(vec_get(a, 0));
    printd(vec_get(a, 1));
    return 0;
}
