
.class public functions
.super java/lang/Object
    
    .method public static fact(I)I
    .limit stack 100
    .limit locals 1
      iload_0
      ldc 1
      if_icmple THEN1
      goto ELSE1
      THEN1:
      ldc 1
      ireturn
      goto CONT1
      ELSE1:
      CONT1:
      iload_0
      iload_0
      ldc 1
      isub
      invokestatic functions/fact(I)I
      imul
      ireturn
    .end method
    
    .method public static _main()I
    .limit stack 100
    .limit locals 0
      ldc 10
      invokestatic functions/fact(I)I
      invokestatic Lib/printd(I)I
      pop
      ldc 0
      ireturn
    .end method

    .method public static main([Ljava/lang/String;)V
    .limit locals 1
    .limit stack 1
      invokestatic functions/_main()I
      pop
      return
    .end method
       