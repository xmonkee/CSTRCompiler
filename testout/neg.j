
.class public neg
.super java/lang/Object
    
    .method public static _main()I
    .limit stack 100
    .limit locals 1
      ldc 123
      istore_0
      iload_0
      ineg
      invokestatic Lib/printd(I)I
      pop
      ldc -123
      invokestatic Lib/printd(I)I
      pop
      ldc 123
      ldc 0
      iadd
      ineg
      invokestatic Lib/printd(I)I
      pop
      iload_0
      ldc 0
      iadd
      ineg
      invokestatic Lib/printd(I)I
      pop
      ldc 0
      ireturn
    .end method

    .method public static main([Ljava/lang/String;)V
    .limit locals 1
    .limit stack 1
      invokestatic neg/_main()I
      pop
      return
    .end method
       