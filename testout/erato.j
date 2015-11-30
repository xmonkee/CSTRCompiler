
.class public erato
.super java/lang/Object
  .field static tab Ljava/lang/String;
  .field static N I
    
    .method public static _main()I
    .limit stack 100
    .limit locals 3
      ldc 100
      istore_2
      ldc "0"
      putstatic erato/tab Ljava/lang/String;
      ldc 1
      istore_0
      LOOP1:
      iload_0
      iload_2
      if_icmplt THEN1
      goto ELSE1
      THEN1:
      getstatic erato/tab Ljava/lang/String;
      ldc "1"
      invokevirtual java/lang/String/concat(Ljava/lang/String;)Ljava/lang/String;
      putstatic erato/tab Ljava/lang/String;
      iload_0
      ldc 1
      iadd
      istore_0
      goto LOOP1
      ELSE1:
      ldc 2
      istore_0
      LOOP2:
      iload_0
      iload_2
      if_icmplt THEN2
      goto ELSE2
      THEN2:
      iload_0
      iload_0
      iadd
      istore_1
      LOOP3:
      iload_1
      iload_2
      if_icmplt THEN3
      goto ELSE3
      THEN3:
      getstatic erato/tab Ljava/lang/String;
      iload_1
      ldc "0"
      invokestatic Lib/put_char_at(Ljava/lang/String;ILjava/lang/String;)Ljava/lang/String;
      putstatic erato/tab Ljava/lang/String;
      iload_1
      iload_0
      iadd
      istore_1
      goto LOOP3
      ELSE3:
      iload_0
      ldc 1
      iadd
      istore_0
      goto LOOP2
      ELSE2:
      ldc 0
      istore_0
      LOOP4:
      iload_0
      iload_2
      if_icmplt THEN4
      goto ELSE4
      THEN4:
      getstatic erato/tab Ljava/lang/String;
      iload_0
      invokestatic Lib/get_char_at(Ljava/lang/String;I)Ljava/lang/String;
      ldc "1"
      invokevirtual java/lang/Object/equals(Ljava/lang/Object;)Z
      ifne THEN5
      goto ELSE5
      THEN5:
        iload_0
        invokestatic Lib/printd(I)I
        pop
        ldc " "
        invokestatic Lib/printf(Ljava/lang/String;)I
        pop
      goto CONT5
      ELSE5:
      CONT5:
      iload_0
      ldc 1
      iadd
      istore_0
      goto LOOP4
      ELSE4:
      ldc "\n"
      invokestatic Lib/printf(Ljava/lang/String;)I
      pop
      ldc 0
      ireturn
    .end method

    .method public static main([Ljava/lang/String;)V
    .limit locals 1
    .limit stack 1
      invokestatic erato/_main()I
      pop
      return
    .end method
       