int print0(int i);
int printf(string s);
int printd(int i);
vec vec_new(int len);
int vec_get(vec v, int idx);
int vec_set(vec v, int idx, int val);
int initvec(vec v, int m);
int initmat(vec m, int M, int N); 

int initvec(vec v, int N){
    int i;
    for(i = 0; i < N; i = i+1)
       vec_set(v, i, i);
    return 0;
}

int initmat(vec m, int M, int N){
    int i,j;
    for(i = 0; i < M; i = i+1)
       for(j = 0; j < N; j = j+1)
           vec_set(m, i*N + j, i*N + j);
    return 0;
}

vec mmul(vec a, vec b, int M, int N){
    vec c;
    int i, j;
    c = vec_new(N);
    for(i = 0; i < M; i = i+1){
        vec_set(c, i, 0);
        for(j = 0; j < N; j = j + 1){
            vec_set(c, i, vec_get(c, i) + vec_get(a, i*N + j) * vec_get(b, j));
        }
    }
    return c;
}        

int main(){
    vec a, b, c;
    int M, N;
    int i, j;
    M = 10;
    N = 10;

    a = vec_new(M*N);
    b = vec_new(N);

    initmat(a, M, N);
    initvec(b, N);
    c = mmul(a, b, M, N);
    for(i = 0; i < N; i = i + 1)
        printd(vec_get(c, i));
    return 0;
}

