
.class public char
.super java/lang/Object
    
    .method public static _main()I
    .limit stack 100
    .limit locals 0
      ldc "a"
      invokestatic Lib/printf(Ljava/lang/String;)I
      pop
      ldc 0
      ireturn
    .end method

    .method public static main([Ljava/lang/String;)V
    .limit locals 1
    .limit stack 1
      invokestatic char/_main()I
      pop
      return
    .end method
       