;entete
extrn lirent:proc, ecrent:proc
extrn ecrbool:proc
extrn ecrch:proc, ligsuiv:proc
.model SMALL
.586

.CODE
debut:
STARTUPCODE



;ouvrePrinc 4
mov bp,sp
sub sp,4


;iconst 8
push word ptr 8


;istore -2
pop ax 
mov word ptr [bp-2], ax


;ecrireChaine "c1="
.DATA
mess0 DB "c1=$"
.CODE
lea dx, mess0
push dx
call ecrch


;iload -2
push word ptr [bp-2]


;ecrireEnt
call ecrent


;aLaLigne
call ligsuiv


FAIRE1:


;iload -2
push word ptr [bp-2]


;iconst 10
push word ptr 10


;iinfegal
pop bx 
pop ax 
cmp ax,bx 
jg $+6 
push -1 
jmp $+4 
push 0 


;iffaux FAIT1
pop ax
cmp ax,0
je FAIT1


;iload -2
push word ptr [bp-2]


;ecrireEnt
call ecrent


;aLaLigne
call ligsuiv


;iload -2
push word ptr [bp-2]


;iconst 1
push word ptr 1


;iadd
pop bx 
pop ax 
add ax,bx
push ax


;istore -2
pop ax 
mov word ptr [bp-2], ax


;goto FAIRE1
jmp FAIRE1


FAIT1:


;iload -2
push word ptr [bp-2]


;iconst 9
push word ptr 9


;isupegal
pop bx 
pop ax 
cmp ax,bx 
jl $+6 
push -1 
jmp $+4 
push 0 


;ecrireBool
call ecrbool


;iload -2
push word ptr [bp-2]


;iconst 9
push word ptr 9


;isupegal
pop bx 
pop ax 
cmp ax,bx 
jl $+6 
push -1 
jmp $+4 
push 0 


;iffaux SINON1
pop ax
cmp ax,0
je SINON1


;iconst 100
push word ptr 100


;istore -2
pop ax 
mov word ptr [bp-2], ax


;goto FSI1
jmp FSI1


SINON1:


FSI1:


;iload -2
push word ptr [bp-2]


;ecrireEnt
call ecrent


;aLaLigne
call ligsuiv


;queue
nop
EXITCODE
End debut
