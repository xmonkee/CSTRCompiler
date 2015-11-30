
.class public loops
.super java/lang/Object
    
    .method public static _main()I
    .limit stack 100
    .limit locals 1
      ldc 0
      istore_0
      LOOP1:
      iload_0
      ldc 10
      if_icmplt THEN1
      goto ELSE1
      THEN1:
        iload_0
        invokestatic Lib/printd(I)I
        pop
        iload_0
        ldc 2
        iadd
        istore_0
      goto LOOP1
      ELSE1:
      ldc -10
      istore_0
      LOOP2:
      iload_0
      ldc 10
      if_icmple THEN2
      goto ELSE2
      THEN2:
      iload_0
      invokestatic Lib/printd(I)I
      pop
      iload_0
      ldc 1
      iadd
      istore_0
      goto LOOP2
      ELSE2:
      ldc 0
      istore_0
      LOOP3:
        iload_0
        invokestatic Lib/printd(I)I
        pop
        iload_0
        ldc 1
        isub
        istore_0
      iload_0
      ldc -20
      if_icmpge LOOP3
      goto ELSE3
      ELSE3:
      ldc 0
      ireturn
    .end method

    .method public static main([Ljava/lang/String;)V
    .limit locals 1
    .limit stack 1
      invokestatic loops/_main()I
      pop
      return
    .end method
       