
.class public cond
.super java/lang/Object
    
    .method public static _main()I
    .limit stack 100
    .limit locals 2
      ldc 450
      istore_0
      ldc -123
      istore_1
      iload_0
      ldc 1
      iadd
      iload_1
      ldc 0
      iadd
      if_icmplt THEN1
      goto ELSE1
      THEN1:
      iload_0
      invokestatic Lib/printd(I)I
      pop
      goto CONT1
      ELSE1:
      iload_1
      invokestatic Lib/printd(I)I
      pop
      CONT1:
      iload_0
      ldc 1
      iadd
      ldc 123
      if_icmplt THEN2
      goto ELSE2
      THEN2:
      iload_0
      invokestatic Lib/printd(I)I
      pop
      goto CONT2
      ELSE2:
      iload_1
      invokestatic Lib/printd(I)I
      pop
      CONT2:
      iload_0
      ldc 1
      iadd
      iload_1
      if_icmplt THEN3
      goto ELSE3
      THEN3:
      iload_0
      invokestatic Lib/printd(I)I
      pop
      goto CONT3
      ELSE3:
      iload_1
      invokestatic Lib/printd(I)I
      pop
      CONT3:
      ldc 45
      iload_1
      ldc 0
      iadd
      if_icmplt THEN4
      goto ELSE4
      THEN4:
      iload_0
      invokestatic Lib/printd(I)I
      pop
      goto CONT4
      ELSE4:
      iload_1
      invokestatic Lib/printd(I)I
      pop
      CONT4:
      ldc 45
      ldc 123
      if_icmplt THEN5
      goto ELSE5
      THEN5:
      iload_0
      invokestatic Lib/printd(I)I
      pop
      goto CONT5
      ELSE5:
      iload_1
      invokestatic Lib/printd(I)I
      pop
      CONT5:
      ldc 45
      iload_1
      if_icmplt THEN6
      goto ELSE6
      THEN6:
      iload_0
      invokestatic Lib/printd(I)I
      pop
      goto CONT6
      ELSE6:
      iload_1
      invokestatic Lib/printd(I)I
      pop
      CONT6:
      iload_0
      iload_1
      ldc 0
      iadd
      if_icmplt THEN7
      goto ELSE7
      THEN7:
      iload_0
      invokestatic Lib/printd(I)I
      pop
      goto CONT7
      ELSE7:
      iload_1
      invokestatic Lib/printd(I)I
      pop
      CONT7:
      iload_0
      ldc 123
      if_icmplt THEN8
      goto ELSE8
      THEN8:
      iload_0
      invokestatic Lib/printd(I)I
      pop
      goto CONT8
      ELSE8:
      iload_1
      invokestatic Lib/printd(I)I
      pop
      CONT8:
      iload_0
      iload_1
      if_icmplt THEN9
      goto ELSE9
      THEN9:
      iload_0
      invokestatic Lib/printd(I)I
      pop
      goto CONT9
      ELSE9:
      iload_1
      invokestatic Lib/printd(I)I
      pop
      CONT9:
      iload_0
      ldc 1
      iadd
      iload_1
      ldc 0
      iadd
      if_icmpgt THEN10
      goto ELSE10
      THEN10:
      iload_0
      invokestatic Lib/printd(I)I
      pop
      goto CONT10
      ELSE10:
      iload_1
      invokestatic Lib/printd(I)I
      pop
      CONT10:
      iload_0
      ldc 1
      iadd
      ldc 123
      if_icmpgt THEN11
      goto ELSE11
      THEN11:
      iload_0
      invokestatic Lib/printd(I)I
      pop
      goto CONT11
      ELSE11:
      iload_1
      invokestatic Lib/printd(I)I
      pop
      CONT11:
      iload_0
      ldc 1
      iadd
      iload_1
      if_icmpgt THEN12
      goto ELSE12
      THEN12:
      iload_0
      invokestatic Lib/printd(I)I
      pop
      goto CONT12
      ELSE12:
      iload_1
      invokestatic Lib/printd(I)I
      pop
      CONT12:
      ldc 45
      iload_1
      ldc 0
      iadd
      if_icmpgt THEN13
      goto ELSE13
      THEN13:
      iload_0
      invokestatic Lib/printd(I)I
      pop
      goto CONT13
      ELSE13:
      iload_1
      invokestatic Lib/printd(I)I
      pop
      CONT13:
      ldc 45
      ldc 123
      if_icmpgt THEN14
      goto ELSE14
      THEN14:
      iload_0
      invokestatic Lib/printd(I)I
      pop
      goto CONT14
      ELSE14:
      iload_1
      invokestatic Lib/printd(I)I
      pop
      CONT14:
      ldc 45
      iload_1
      if_icmpgt THEN15
      goto ELSE15
      THEN15:
      iload_0
      invokestatic Lib/printd(I)I
      pop
      goto CONT15
      ELSE15:
      iload_1
      invokestatic Lib/printd(I)I
      pop
      CONT15:
      iload_0
      iload_1
      ldc 0
      iadd
      if_icmpgt THEN16
      goto ELSE16
      THEN16:
      iload_0
      invokestatic Lib/printd(I)I
      pop
      goto CONT16
      ELSE16:
      iload_1
      invokestatic Lib/printd(I)I
      pop
      CONT16:
      iload_0
      ldc 123
      if_icmpgt THEN17
      goto ELSE17
      THEN17:
      iload_0
      invokestatic Lib/printd(I)I
      pop
      goto CONT17
      ELSE17:
      iload_1
      invokestatic Lib/printd(I)I
      pop
      CONT17:
      iload_0
      iload_1
      if_icmpgt THEN18
      goto ELSE18
      THEN18:
      iload_0
      invokestatic Lib/printd(I)I
      pop
      goto CONT18
      ELSE18:
      iload_1
      invokestatic Lib/printd(I)I
      pop
      CONT18:
      iload_0
      ldc 1
      iadd
      iload_1
      ldc 0
      iadd
      if_icmpge THEN19
      goto ELSE19
      THEN19:
      iload_0
      invokestatic Lib/printd(I)I
      pop
      goto CONT19
      ELSE19:
      iload_1
      invokestatic Lib/printd(I)I
      pop
      CONT19:
      iload_0
      ldc 1
      iadd
      ldc 123
      if_icmpge THEN20
      goto ELSE20
      THEN20:
      iload_0
      invokestatic Lib/printd(I)I
      pop
      goto CONT20
      ELSE20:
      iload_1
      invokestatic Lib/printd(I)I
      pop
      CONT20:
      iload_0
      ldc 1
      iadd
      iload_1
      if_icmpge THEN21
      goto ELSE21
      THEN21:
      iload_0
      invokestatic Lib/printd(I)I
      pop
      goto CONT21
      ELSE21:
      iload_1
      invokestatic Lib/printd(I)I
      pop
      CONT21:
      ldc 45
      iload_1
      ldc 0
      iadd
      if_icmpge THEN22
      goto ELSE22
      THEN22:
      iload_0
      invokestatic Lib/printd(I)I
      pop
      goto CONT22
      ELSE22:
      iload_1
      invokestatic Lib/printd(I)I
      pop
      CONT22:
      ldc 45
      ldc 123
      if_icmpge THEN23
      goto ELSE23
      THEN23:
      iload_0
      invokestatic Lib/printd(I)I
      pop
      goto CONT23
      ELSE23:
      iload_1
      invokestatic Lib/printd(I)I
      pop
      CONT23:
      ldc 45
      iload_1
      if_icmpge THEN24
      goto ELSE24
      THEN24:
      iload_0
      invokestatic Lib/printd(I)I
      pop
      goto CONT24
      ELSE24:
      iload_1
      invokestatic Lib/printd(I)I
      pop
      CONT24:
      iload_0
      iload_1
      ldc 0
      iadd
      if_icmpge THEN25
      goto ELSE25
      THEN25:
      iload_0
      invokestatic Lib/printd(I)I
      pop
      goto CONT25
      ELSE25:
      iload_1
      invokestatic Lib/printd(I)I
      pop
      CONT25:
      iload_0
      ldc 123
      if_icmpge THEN26
      goto ELSE26
      THEN26:
      iload_0
      invokestatic Lib/printd(I)I
      pop
      goto CONT26
      ELSE26:
      iload_1
      invokestatic Lib/printd(I)I
      pop
      CONT26:
      iload_0
      iload_1
      if_icmpge THEN27
      goto ELSE27
      THEN27:
      iload_0
      invokestatic Lib/printd(I)I
      pop
      goto CONT27
      ELSE27:
      iload_1
      invokestatic Lib/printd(I)I
      pop
      CONT27:
      iload_0
      ldc 1
      iadd
      iload_1
      ldc 0
      iadd
      if_icmple THEN28
      goto ELSE28
      THEN28:
      iload_0
      invokestatic Lib/printd(I)I
      pop
      goto CONT28
      ELSE28:
      iload_1
      invokestatic Lib/printd(I)I
      pop
      CONT28:
      iload_0
      ldc 1
      iadd
      ldc 123
      if_icmple THEN29
      goto ELSE29
      THEN29:
      iload_0
      invokestatic Lib/printd(I)I
      pop
      goto CONT29
      ELSE29:
      iload_1
      invokestatic Lib/printd(I)I
      pop
      CONT29:
      iload_0
      ldc 1
      iadd
      iload_1
      if_icmple THEN30
      goto ELSE30
      THEN30:
      iload_0
      invokestatic Lib/printd(I)I
      pop
      goto CONT30
      ELSE30:
      iload_1
      invokestatic Lib/printd(I)I
      pop
      CONT30:
      ldc 45
      iload_1
      ldc 0
      iadd
      if_icmple THEN31
      goto ELSE31
      THEN31:
      iload_0
      invokestatic Lib/printd(I)I
      pop
      goto CONT31
      ELSE31:
      iload_1
      invokestatic Lib/printd(I)I
      pop
      CONT31:
      ldc 45
      ldc 123
      if_icmple THEN32
      goto ELSE32
      THEN32:
      iload_0
      invokestatic Lib/printd(I)I
      pop
      goto CONT32
      ELSE32:
      iload_1
      invokestatic Lib/printd(I)I
      pop
      CONT32:
      ldc 45
      iload_1
      if_icmple THEN33
      goto ELSE33
      THEN33:
      iload_0
      invokestatic Lib/printd(I)I
      pop
      goto CONT33
      ELSE33:
      iload_1
      invokestatic Lib/printd(I)I
      pop
      CONT33:
      iload_0
      iload_1
      ldc 0
      iadd
      if_icmple THEN34
      goto ELSE34
      THEN34:
      iload_0
      invokestatic Lib/printd(I)I
      pop
      goto CONT34
      ELSE34:
      iload_1
      invokestatic Lib/printd(I)I
      pop
      CONT34:
      iload_0
      ldc 123
      if_icmple THEN35
      goto ELSE35
      THEN35:
      iload_0
      invokestatic Lib/printd(I)I
      pop
      goto CONT35
      ELSE35:
      iload_1
      invokestatic Lib/printd(I)I
      pop
      CONT35:
      iload_0
      iload_1
      if_icmple THEN36
      goto ELSE36
      THEN36:
      iload_0
      invokestatic Lib/printd(I)I
      pop
      goto CONT36
      ELSE36:
      iload_1
      invokestatic Lib/printd(I)I
      pop
      CONT36:
      iload_0
      ldc 1
      iadd
      iload_1
      ldc 0
      iadd
      if_icmpeq THEN37
      goto ELSE37
      THEN37:
      iload_0
      invokestatic Lib/printd(I)I
      pop
      goto CONT37
      ELSE37:
      iload_1
      invokestatic Lib/printd(I)I
      pop
      CONT37:
      iload_0
      ldc 1
      iadd
      ldc 123
      if_icmpeq THEN38
      goto ELSE38
      THEN38:
      iload_0
      invokestatic Lib/printd(I)I
      pop
      goto CONT38
      ELSE38:
      iload_1
      invokestatic Lib/printd(I)I
      pop
      CONT38:
      iload_0
      ldc 1
      iadd
      iload_1
      if_icmpeq THEN39
      goto ELSE39
      THEN39:
      iload_0
      invokestatic Lib/printd(I)I
      pop
      goto CONT39
      ELSE39:
      iload_1
      invokestatic Lib/printd(I)I
      pop
      CONT39:
      ldc 45
      iload_1
      ldc 0
      iadd
      if_icmpeq THEN40
      goto ELSE40
      THEN40:
      iload_0
      invokestatic Lib/printd(I)I
      pop
      goto CONT40
      ELSE40:
      iload_1
      invokestatic Lib/printd(I)I
      pop
      CONT40:
      ldc 45
      ldc 123
      if_icmpeq THEN41
      goto ELSE41
      THEN41:
      iload_0
      invokestatic Lib/printd(I)I
      pop
      goto CONT41
      ELSE41:
      iload_1
      invokestatic Lib/printd(I)I
      pop
      CONT41:
      ldc 45
      iload_1
      if_icmpeq THEN42
      goto ELSE42
      THEN42:
      iload_0
      invokestatic Lib/printd(I)I
      pop
      goto CONT42
      ELSE42:
      iload_1
      invokestatic Lib/printd(I)I
      pop
      CONT42:
      iload_0
      iload_1
      ldc 0
      iadd
      if_icmpeq THEN43
      goto ELSE43
      THEN43:
      iload_0
      invokestatic Lib/printd(I)I
      pop
      goto CONT43
      ELSE43:
      iload_1
      invokestatic Lib/printd(I)I
      pop
      CONT43:
      iload_0
      ldc 123
      if_icmpeq THEN44
      goto ELSE44
      THEN44:
      iload_0
      invokestatic Lib/printd(I)I
      pop
      goto CONT44
      ELSE44:
      iload_1
      invokestatic Lib/printd(I)I
      pop
      CONT44:
      iload_0
      iload_1
      if_icmpeq THEN45
      goto ELSE45
      THEN45:
      iload_0
      invokestatic Lib/printd(I)I
      pop
      goto CONT45
      ELSE45:
      iload_1
      invokestatic Lib/printd(I)I
      pop
      CONT45:
      iload_0
      ldc 1
      iadd
      iload_1
      ldc 0
      iadd
      if_icmpne THEN46
      goto ELSE46
      THEN46:
      iload_0
      invokestatic Lib/printd(I)I
      pop
      goto CONT46
      ELSE46:
      iload_1
      invokestatic Lib/printd(I)I
      pop
      CONT46:
      iload_0
      ldc 1
      iadd
      ldc 123
      if_icmpne THEN47
      goto ELSE47
      THEN47:
      iload_0
      invokestatic Lib/printd(I)I
      pop
      goto CONT47
      ELSE47:
      iload_1
      invokestatic Lib/printd(I)I
      pop
      CONT47:
      iload_0
      ldc 1
      iadd
      iload_1
      if_icmpne THEN48
      goto ELSE48
      THEN48:
      iload_0
      invokestatic Lib/printd(I)I
      pop
      goto CONT48
      ELSE48:
      iload_1
      invokestatic Lib/printd(I)I
      pop
      CONT48:
      ldc 45
      iload_1
      ldc 0
      iadd
      if_icmpne THEN49
      goto ELSE49
      THEN49:
      iload_0
      invokestatic Lib/printd(I)I
      pop
      goto CONT49
      ELSE49:
      iload_1
      invokestatic Lib/printd(I)I
      pop
      CONT49:
      ldc 45
      ldc 123
      if_icmpne THEN50
      goto ELSE50
      THEN50:
      iload_0
      invokestatic Lib/printd(I)I
      pop
      goto CONT50
      ELSE50:
      iload_1
      invokestatic Lib/printd(I)I
      pop
      CONT50:
      ldc 45
      iload_1
      if_icmpne THEN51
      goto ELSE51
      THEN51:
      iload_0
      invokestatic Lib/printd(I)I
      pop
      goto CONT51
      ELSE51:
      iload_1
      invokestatic Lib/printd(I)I
      pop
      CONT51:
      iload_0
      iload_1
      ldc 0
      iadd
      if_icmpne THEN52
      goto ELSE52
      THEN52:
      iload_0
      invokestatic Lib/printd(I)I
      pop
      goto CONT52
      ELSE52:
      iload_1
      invokestatic Lib/printd(I)I
      pop
      CONT52:
      iload_0
      ldc 123
      if_icmpne THEN53
      goto ELSE53
      THEN53:
      iload_0
      invokestatic Lib/printd(I)I
      pop
      goto CONT53
      ELSE53:
      iload_1
      invokestatic Lib/printd(I)I
      pop
      CONT53:
      iload_0
      iload_1
      if_icmpne THEN54
      goto ELSE54
      THEN54:
      iload_0
      invokestatic Lib/printd(I)I
      pop
      goto CONT54
      ELSE54:
      iload_1
      invokestatic Lib/printd(I)I
      pop
      CONT54:
      ldc 0
      ireturn
    .end method

    .method public static main([Ljava/lang/String;)V
    .limit locals 1
    .limit stack 1
      invokestatic cond/_main()I
      pop
      return
    .end method
       