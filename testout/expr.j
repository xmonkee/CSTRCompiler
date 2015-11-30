
.class public expr
.super java/lang/Object
    
    .method public static _main()I
    .limit stack 100
    .limit locals 3
      ldc 45000
      istore_0
      ldc -123
      istore_1
      ldc 43
      istore_2
      iload_0
      iload_1
      iadd
      iload_2
      imul
      ldc 100
      idiv
      iload_1
      iload_2
      imul
      iload_0
      irem
      iadd
      iload_1
      isub
      iload_2
      ishl
      iload_2
      iload_1
      isub
      ldc 2
      ishr
      idiv
      invokestatic Lib/printd(I)I
      pop
      ldc 0
      ireturn
    .end method

    .method public static main([Ljava/lang/String;)V
    .limit locals 1
    .limit stack 1
      invokestatic expr/_main()I
      pop
      return
    .end method
       