
.class public string
.super java/lang/Object
  .field static u Ljava/lang/String;
    
    .method public static _main()I
    .limit stack 100
    .limit locals 2
      ldc "hello"
      astore_0
      ldc " world\n"
      astore_1
      ldc "bye"
      putstatic string/u Ljava/lang/String;
      ldc "hello"
      invokestatic Lib/printf(Ljava/lang/String;)I
      pop
      ldc "hello"
      ldc "world\n"
      invokestatic string/cat(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
      invokestatic Lib/printf(Ljava/lang/String;)I
      pop
      aload_0
      ldc "world\n"
      invokestatic string/cat(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
      invokestatic Lib/printf(Ljava/lang/String;)I
      pop
      ldc "hello"
      aload_1
      invokestatic string/cat(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
      invokestatic Lib/printf(Ljava/lang/String;)I
      pop
      aload_0
      aload_1
      invokestatic string/cat(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
      invokestatic Lib/printf(Ljava/lang/String;)I
      pop
      aload_0
      getstatic string/u Ljava/lang/String;
      invokestatic string/cat(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
      aload_1
      invokestatic string/cat(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
      invokestatic Lib/printf(Ljava/lang/String;)I
      pop
      aload_0
      getstatic string/u Ljava/lang/String;
      aload_1
      invokestatic string/cat(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
      invokestatic string/cat(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
      invokestatic Lib/printf(Ljava/lang/String;)I
      pop
      aload_0
      aload_0
      invokestatic string/cat(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
      aload_1
      aload_1
      invokestatic string/cat(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
      invokestatic string/cat(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
      invokestatic Lib/printf(Ljava/lang/String;)I
      pop
      aload_0
      getstatic string/u Ljava/lang/String;
      invokestatic string/cat(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
      aload_1
      invokestatic string/cat(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
      invokestatic Lib/printf(Ljava/lang/String;)I
      pop
      ldc 0
      ireturn
    .end method
    
    .method public static cat(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    .limit stack 100
    .limit locals 2
      aload_0
      aload_1
      invokevirtual java/lang/String/concat(Ljava/lang/String;)Ljava/lang/String;
      areturn
    .end method

    .method public static main([Ljava/lang/String;)V
    .limit locals 1
    .limit stack 1
      invokestatic string/_main()I
      pop
      return
    .end method
       