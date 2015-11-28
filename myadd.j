
.class public myadd
.super java/lang/Object
  .field static a I
  .field static b I
  .field static c Ljava/lang/String;
    
    .method public static _main()I
    .limit stack 100
    .limit locals 2
      ldc 45000
      istore_0
      ldc -123
      istore_1
      getstatic myadd/a I
      pop
      ldc 2
      putstatic myadd/a I
      iload_0
      iload_1
      iadd
      invokestatic myadd/printd(I)I
      pop
      iload_0
      ldc 10
      if_icmplt T1
      goto F1
      T1:
        ldc 10
        invokestatic myadd/printd(I)I
        pop
      goto N1
      F1:
        ldc 100
        invokestatic myadd/printd(I)I
        pop
      N1:
      iload_0
      ldc 10
      if_icmpgt T2
      goto F2
      T2:
        ldc 10
        invokestatic myadd/printd(I)I
        pop
      goto N2
      F2:
        ldc 100
        invokestatic myadd/printd(I)I
        pop
      N2:
      iload_0
      ldc 10
      if_icmpgt T3
      goto F3
      T3:
        ldc 50
        invokestatic myadd/printd(I)I
        pop
      goto N3
      F3:
      N3:
      iload_0
      ldc 10
      if_icmplt T4
      goto F4
      T4:
        ldc 0
        invokestatic myadd/printd(I)I
        pop
      goto N4
      F4:
      N4:
      ldc 0
      ireturn
    .end method

    .method public static main([Ljava/lang/String;)V
    .limit locals 1
    .limit stack 1
      invokestatic myadd/_main()I
      pop
      return
    .end method

    .method public static printd(I)I
    .limit locals 1
    .limit stack 2
      getstatic      java/lang/System/out Ljava/io/PrintStream;
      iload_0
      invokevirtual  java/io/PrintStream/println(I)V
      iconst_0
      ireturn
    .end method
       