
.class public sub
.super java/lang/Object
    
    .method public static _main()I
    .limit stack 100
    .limit locals 2
      ldc 45000
      istore_0
      ldc -123
      istore_1
      iload_0
      iload_1
      isub
      invokestatic Lib/printd(I)I
      pop
      ldc 45000
      iload_1
      isub
      invokestatic Lib/printd(I)I
      pop
      iload_0
      ldc 123
      isub
      invokestatic Lib/printd(I)I
      pop
      ldc 45000
      ldc 123
      isub
      invokestatic Lib/printd(I)I
      pop
      iload_0
      iload_1
      ldc 0
      iadd
      isub
      invokestatic Lib/printd(I)I
      pop
      iload_0
      ldc 0
      iadd
      iload_1
      isub
      invokestatic Lib/printd(I)I
      pop
      iload_0
      ldc 0
      iadd
      iload_1
      ldc 0
      iadd
      isub
      invokestatic Lib/printd(I)I
      pop
      iload_0
      ldc 0
      iadd
      ldc 123
      isub
      invokestatic Lib/printd(I)I
      pop
      ldc 45000
      iload_1
      ldc 0
      iadd
      isub
      invokestatic Lib/printd(I)I
      pop
      ldc 0
      ireturn
    .end method

    .method public static main([Ljava/lang/String;)V
    .limit locals 1
    .limit stack 1
      invokestatic sub/_main()I
      pop
      return
    .end method
       