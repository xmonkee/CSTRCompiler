
.class public matrix
.super java/lang/Object
    
    .method public static initvec([II)I
    .limit stack 100
    .limit locals 3
      ldc 0
      istore_2
      LOOP1:
      iload_2
      iload_1
      if_icmplt THEN1
      goto ELSE1
      THEN1:
      aload_0
      iload_2
      iload_2
      invokestatic Lib/vec_set([III)I
      pop
      iload_2
      ldc 1
      iadd
      istore_2
      goto LOOP1
      ELSE1:
      ldc 0
      ireturn
    .end method
    
    .method public static initmat([III)I
    .limit stack 100
    .limit locals 5
      ldc 0
      istore_3
      LOOP2:
      iload_3
      iload_1
      if_icmplt THEN2
      goto ELSE2
      THEN2:
      ldc 0
      istore 4
      LOOP3:
      iload 4
      iload_2
      if_icmplt THEN3
      goto ELSE3
      THEN3:
      aload_0
      iload_3
      iload_2
      imul
      iload 4
      iadd
      iload_3
      iload_2
      imul
      iload 4
      iadd
      invokestatic Lib/vec_set([III)I
      pop
      iload 4
      ldc 1
      iadd
      istore 4
      goto LOOP3
      ELSE3:
      iload_3
      ldc 1
      iadd
      istore_3
      goto LOOP2
      ELSE2:
      ldc 0
      ireturn
    .end method
    
    .method public static mmul([I[III)[I
    .limit stack 100
    .limit locals 7
      iload_3
      invokestatic Lib/vec_new(I)[I
      astore 4
      ldc 0
      istore 5
      LOOP4:
      iload 5
      iload_2
      if_icmplt THEN4
      goto ELSE4
      THEN4:
        aload 4
        iload 5
        ldc 0
        invokestatic Lib/vec_set([III)I
        pop
        ldc 0
        istore 6
        LOOP5:
        iload 6
        iload_3
        if_icmplt THEN5
        goto ELSE5
        THEN5:
          iload 5
          invokestatic Lib/print0(I)I
          pop
          iload 6
          invokestatic Lib/print0(I)I
          pop
          aload_0
          iload 5
          iload_3
          imul
          iload 6
          iadd
          invokestatic Lib/vec_get([II)I
          invokestatic Lib/print0(I)I
          pop
          aload_1
          iload 6
          invokestatic Lib/vec_get([II)I
          invokestatic Lib/print0(I)I
          pop
          ldc ""
          invokestatic Lib/printf(Ljava/lang/String;)I
          pop
          aload 4
          iload 5
          aload 4
          iload 5
          invokestatic Lib/vec_get([II)I
          aload_0
          iload 5
          iload_3
          imul
          iload 6
          iadd
          invokestatic Lib/vec_get([II)I
          aload_1
          iload 6
          invokestatic Lib/vec_get([II)I
          imul
          iadd
          invokestatic Lib/vec_set([III)I
          pop
        iload 6
        ldc 1
        iadd
        istore 6
        goto LOOP5
        ELSE5:
      iload 5
      ldc 1
      iadd
      istore 5
      goto LOOP4
      ELSE4:
      aload 4
      areturn
    .end method
    
    .method public static _main()I
    .limit stack 100
    .limit locals 7
      ldc 10
      istore_3
      ldc 10
      istore 4
      iload_3
      iload 4
      imul
      invokestatic Lib/vec_new(I)[I
      astore_0
      iload 4
      invokestatic Lib/vec_new(I)[I
      astore_1
      aload_0
      iload_3
      iload 4
      invokestatic matrix/initmat([III)I
      pop
      aload_1
      iload 4
      invokestatic matrix/initvec([II)I
      pop
      aload_0
      aload_1
      iload_3
      iload 4
      invokestatic matrix/mmul([I[III)[I
      astore_2
      ldc 0
      istore 5
      LOOP6:
      iload 5
      iload 4
      if_icmplt THEN6
      goto ELSE6
      THEN6:
      aload_2
      iload 5
      invokestatic Lib/vec_get([II)I
      invokestatic Lib/printd(I)I
      pop
      iload 5
      ldc 1
      iadd
      istore 5
      goto LOOP6
      ELSE6:
      ldc 0
      ireturn
    .end method

    .method public static main([Ljava/lang/String;)V
    .limit locals 1
    .limit stack 1
      invokestatic matrix/_main()I
      pop
      return
    .end method
       