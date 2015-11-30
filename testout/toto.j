
.class public toto
.super java/lang/Object
    
    .method public static _main()I
    .limit stack 100
    .limit locals 1
      ldc 0
      istore_0
      LOOP1:
      iload_0
      ldc 1000
      if_icmplt THEN1
      goto ELSE1
      THEN1:
        iload_0
        invokestatic Lib/printd(I)I
        pop
        ldc "\n"
        invokestatic Lib/printf(Ljava/lang/String;)I
        pop
        ldc 1
        invokestatic Lib/sleep(I)I
        pop
      iload_0
      ldc 1
      iadd
      istore_0
      goto LOOP1
      ELSE1:
      ldc 0
      ireturn
    .end method

    .method public static main([Ljava/lang/String;)V
    .limit locals 1
    .limit stack 1
      invokestatic toto/_main()I
      pop
      return
    .end method
       