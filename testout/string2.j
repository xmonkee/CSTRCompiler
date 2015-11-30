
.class public string2
.super java/lang/Object
  .field static u Ljava/lang/String;
    
    .method public static _main()I
    .limit stack 100
    .limit locals 2
      ldc "hello"
      astore_0
      ldc "helll"
      astore_1
      ldc "hellp"
      putstatic string2/u Ljava/lang/String;
      aload_0
      aload_1
      invokevirtual java/lang/Object/equals(Ljava/lang/Object;)Z
      ifne THEN1
      goto ELSE1
      THEN1:
      ldc 1
      invokestatic Lib/printd(I)I
      pop
      goto CONT1
      ELSE1:
      ldc 0
      invokestatic Lib/printd(I)I
      pop
      CONT1:
      ldc "abc"
      ldc "abc"
      invokevirtual java/lang/Object/equals(Ljava/lang/Object;)Z
      ifne THEN2
      goto ELSE2
      THEN2:
      ldc 1
      invokestatic Lib/printd(I)I
      pop
      goto CONT2
      ELSE2:
      ldc 0
      invokestatic Lib/printd(I)I
      pop
      CONT2:
      aload_1
      aload_0
      invokevirtual java/lang/Object/equals(Ljava/lang/Object;)Z
      ifne THEN3
      goto ELSE3
      THEN3:
      ldc 1
      invokestatic Lib/printd(I)I
      pop
      goto CONT3
      ELSE3:
      ldc 0
      invokestatic Lib/printd(I)I
      pop
      CONT3:
      aload_1
      ldc "abc"
      invokevirtual java/lang/String/concat(Ljava/lang/String;)Ljava/lang/String;
      getstatic string2/u Ljava/lang/String;
      ldc "ab"
      invokevirtual java/lang/String/concat(Ljava/lang/String;)Ljava/lang/String;
      invokevirtual java/lang/Object/equals(Ljava/lang/Object;)Z
      ifne THEN4
      goto ELSE4
      THEN4:
      ldc 1
      invokestatic Lib/printd(I)I
      pop
      goto CONT4
      ELSE4:
      ldc 0
      invokestatic Lib/printd(I)I
      pop
      CONT4:
      aload_0
      aload_1
      invokevirtual java/lang/String/concat(Ljava/lang/String;)Ljava/lang/String;
      getstatic string2/u Ljava/lang/String;
      invokevirtual java/lang/String/concat(Ljava/lang/String;)Ljava/lang/String;
      aload_0
      aload_1
      invokevirtual java/lang/String/concat(Ljava/lang/String;)Ljava/lang/String;
      getstatic string2/u Ljava/lang/String;
      invokevirtual java/lang/String/concat(Ljava/lang/String;)Ljava/lang/String;
      invokevirtual java/lang/Object/equals(Ljava/lang/Object;)Z
      ifne THEN5
      goto ELSE5
      THEN5:
      ldc 1
      invokestatic Lib/printd(I)I
      pop
      goto CONT5
      ELSE5:
      ldc 0
      invokestatic Lib/printd(I)I
      pop
      CONT5:
      aload_0
      aload_1
      invokevirtual java/lang/Object/equals(Ljava/lang/Object;)Z
      ifeq THEN6
      goto ELSE6
      THEN6:
      ldc 1
      invokestatic Lib/printd(I)I
      pop
      goto CONT6
      ELSE6:
      ldc 0
      invokestatic Lib/printd(I)I
      pop
      CONT6:
      ldc "abc"
      ldc "abc"
      invokevirtual java/lang/Object/equals(Ljava/lang/Object;)Z
      ifeq THEN7
      goto ELSE7
      THEN7:
      ldc 1
      invokestatic Lib/printd(I)I
      pop
      goto CONT7
      ELSE7:
      ldc 0
      invokestatic Lib/printd(I)I
      pop
      CONT7:
      aload_1
      aload_0
      invokevirtual java/lang/Object/equals(Ljava/lang/Object;)Z
      ifeq THEN8
      goto ELSE8
      THEN8:
      ldc 1
      invokestatic Lib/printd(I)I
      pop
      goto CONT8
      ELSE8:
      ldc 0
      invokestatic Lib/printd(I)I
      pop
      CONT8:
      aload_1
      ldc "abc"
      invokevirtual java/lang/String/concat(Ljava/lang/String;)Ljava/lang/String;
      getstatic string2/u Ljava/lang/String;
      ldc "ab"
      invokevirtual java/lang/String/concat(Ljava/lang/String;)Ljava/lang/String;
      invokevirtual java/lang/Object/equals(Ljava/lang/Object;)Z
      ifeq THEN9
      goto ELSE9
      THEN9:
      ldc 1
      invokestatic Lib/printd(I)I
      pop
      goto CONT9
      ELSE9:
      ldc 0
      invokestatic Lib/printd(I)I
      pop
      CONT9:
      aload_0
      aload_1
      invokevirtual java/lang/String/concat(Ljava/lang/String;)Ljava/lang/String;
      getstatic string2/u Ljava/lang/String;
      invokevirtual java/lang/String/concat(Ljava/lang/String;)Ljava/lang/String;
      aload_0
      aload_1
      invokevirtual java/lang/String/concat(Ljava/lang/String;)Ljava/lang/String;
      getstatic string2/u Ljava/lang/String;
      invokevirtual java/lang/String/concat(Ljava/lang/String;)Ljava/lang/String;
      invokevirtual java/lang/Object/equals(Ljava/lang/Object;)Z
      ifeq THEN10
      goto ELSE10
      THEN10:
      ldc 1
      invokestatic Lib/printd(I)I
      pop
      goto CONT10
      ELSE10:
      ldc 0
      invokestatic Lib/printd(I)I
      pop
      CONT10:
      ldc 0
      ireturn
    .end method

    .method public static main([Ljava/lang/String;)V
    .limit locals 1
    .limit stack 1
      invokestatic string2/_main()I
      pop
      return
    .end method
       