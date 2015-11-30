
.class public string3
.super java/lang/Object
    
    .method public static sub(Ljava/lang/String;II)Ljava/lang/String;
    .limit stack 100
    .limit locals 5
      ldc ""
      astore 4
      iload_1
      istore_3
      LOOP1:
      iload_3
      iload_2
      if_icmple THEN1
      goto ELSE1
      THEN1:
      aload 4
      aload_0
      iload_3
      invokestatic Lib/get_char_at(Ljava/lang/String;I)Ljava/lang/String;
      invokevirtual java/lang/String/concat(Ljava/lang/String;)Ljava/lang/String;
      astore 4
      iload_3
      ldc 1
      iadd
      istore_3
      goto LOOP1
      ELSE1:
      aload 4
      areturn
    .end method
    
    .method public static invert(Ljava/lang/String;)Ljava/lang/String;
    .limit stack 100
    .limit locals 1
      aload_0
      ldc ""
      invokevirtual java/lang/Object/equals(Ljava/lang/Object;)Z
      ifne THEN2
      goto ELSE2
      THEN2:
      ldc ""
      areturn
      goto CONT2
      ELSE2:
      CONT2:
      aload_0
      ldc 1
      aload_0
      invokestatic Lib/strlen(Ljava/lang/String;)I
      ldc 1
      isub
      invokestatic string3/sub(Ljava/lang/String;II)Ljava/lang/String;
      invokestatic string3/invert(Ljava/lang/String;)Ljava/lang/String;
      aload_0
      ldc 0
      invokestatic Lib/get_char_at(Ljava/lang/String;I)Ljava/lang/String;
      invokevirtual java/lang/String/concat(Ljava/lang/String;)Ljava/lang/String;
      areturn
    .end method
    
    .method public static _main()I
    .limit stack 100
    .limit locals 2
      ldc "hello"
      astore_0
      ldc "world"
      astore_1
      aload_0
      aload_1
      invokevirtual java/lang/String/concat(Ljava/lang/String;)Ljava/lang/String;
      aload_1
      aload_0
      invokevirtual java/lang/String/concat(Ljava/lang/String;)Ljava/lang/String;
      invokestatic string3/invert(Ljava/lang/String;)Ljava/lang/String;
      invokevirtual java/lang/String/concat(Ljava/lang/String;)Ljava/lang/String;
      invokestatic Lib/printf(Ljava/lang/String;)I
      pop
      aload_1
      invokestatic string3/invert(Ljava/lang/String;)Ljava/lang/String;
      invokestatic string3/invert(Ljava/lang/String;)Ljava/lang/String;
      invokestatic string3/invert(Ljava/lang/String;)Ljava/lang/String;
      invokestatic string3/invert(Ljava/lang/String;)Ljava/lang/String;
      invokestatic string3/invert(Ljava/lang/String;)Ljava/lang/String;
      invokestatic string3/invert(Ljava/lang/String;)Ljava/lang/String;
      invokestatic string3/invert(Ljava/lang/String;)Ljava/lang/String;
      invokestatic string3/invert(Ljava/lang/String;)Ljava/lang/String;
      invokestatic Lib/printf(Ljava/lang/String;)I
      pop
      ldc 0
      ireturn
    .end method

    .method public static main([Ljava/lang/String;)V
    .limit locals 1
    .limit stack 1
      invokestatic string3/_main()I
      pop
      return
    .end method
       