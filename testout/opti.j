
.class public opti
.super java/lang/Object
    
    .method public static _main()I
    .limit stack 100
    .limit locals 2
      ldc ""
      astore_1
      ldc 0
      ldc 0
      ldc 100
      imul
      if_icmpeq THEN1
      goto ELSE1
      THEN1:
      ldc 11
      ldc 1
      ldc 4
      imul
      iadd
      istore_0
      goto CONT1
      ELSE1:
      ldc 12
      ldc 2
      iadd
      istore_0
      CONT1:
      ldc "a"
      ldc "aa"
      invokevirtual java/lang/String/concat(Ljava/lang/String;)Ljava/lang/String;
      ldc "aa"
      ldc "a"
      invokevirtual java/lang/String/concat(Ljava/lang/String;)Ljava/lang/String;
      invokevirtual java/lang/Object/equals(Ljava/lang/Object;)Z
      ifeq THEN2
      goto ELSE2
      THEN2:
      ldc "abc"
      ldc "def"
      invokevirtual java/lang/String/concat(Ljava/lang/String;)Ljava/lang/String;
      astore_1
      goto CONT2
      ELSE2:
      CONT2:
      iload_0
      istore_0
      LOOP3:
      ldc 0
      ldc 1
      if_icmpgt THEN3
      goto ELSE3
      THEN3:
      iload_0
      ldc 1
      iadd
      istore_0
      aload_1
      ldc "1"
      invokevirtual java/lang/String/concat(Ljava/lang/String;)Ljava/lang/String;
      astore_1
      goto LOOP3
      ELSE3:
      iload_0
      ireturn
    .end method

    .method public static main([Ljava/lang/String;)V
    .limit locals 1
    .limit stack 1
      invokestatic opti/_main()I
      pop
      return
    .end method
       