;entete
extrn lirent:proc, ecrent:proc
extrn ecrbool:proc
extrn ecrch:proc, ligsuiv:proc
.model SMALL
.586

.CODE

max: 


;ouvbloc 6
enter 6,0

;iload 6
push word ptr [bp+6]


;istore -6
pop ax 
mov word ptr [bp-6], ax


;iload -6
push word ptr [bp-6]


;iload 4
push word ptr [bp+4]


;isup
pop bx 
pop ax 
cmp ax,bx 
jle $+6 
push -1 
jmp $+4 
push 0 


;iffaux SINON1
pop ax
cmp ax,0
je SINON1


;iload -6
push word ptr [bp-6]


;ireturn 8
pop ax
mov [bp+8], ax


;goto FSI1
jmp FSI1


SINON1:


;iload 4
push word ptr [bp+4]


;ireturn 8
pop ax
mov [bp+8], ax


FSI1:


;fermebloc 4
leave
ret 4
min: 


;ouvbloc 0
enter 0,0

;iload 6
push word ptr [bp+6]


;iload 4
push word ptr [bp+4]


;iinf
pop bx 
pop ax 
cmp ax,bx 
jge $+6 
push -1 
jmp $+4 
push 0 


;iffaux SINON2
pop ax
cmp ax,0
je SINON2


;iload 6
push word ptr [bp+6]


;ireturn 8
pop ax
mov [bp+8], ax


;goto FSI2
jmp FSI2


SINON2:


;iload 4
push word ptr [bp+4]


;ireturn 8
pop ax
mov [bp+8], ax


FSI2:


;fermebloc 4
leave
ret 4
sup: 


;ouvbloc 0
enter 0,0

;iload 6
push word ptr [bp+6]


;iload 4
push word ptr [bp+4]


;isup
pop bx 
pop ax 
cmp ax,bx 
jle $+6 
push -1 
jmp $+4 
push 0 


;ireturn 8
pop ax
mov [bp+8], ax


;fermebloc 4
leave
ret 4


debut:
STARTUPCODE

main:

;ouvbloc 8
enter 8,0

;iconst -1
push word ptr -1


;istore -8
pop ax 
mov word ptr [bp-8], ax


FAIRE1:


;iload -8
push word ptr [bp-8]


;iffaux FAIT1
pop ax
cmp ax,0
je FAIT1


;ecrireChaine "Entrez parametre : "
.DATA
mess0 DB "Entrez parametre : $"
.CODE
lea dx, mess0
push dx
call ecrch


;lireEnt -6
lea dx,[bp-6]
push dx
call lirent


;aLaLigne
call ligsuiv


;ecrireChaine "Superieur a 10 ? "
.DATA
mess1 DB "Superieur a 10 ? $"
.CODE
lea dx, mess1
push dx
call ecrch


;reserveRetour
sub sp,2


;iload -6
push word ptr [bp-6]


;iconst 10
push word ptr 10


;call sup
call sup


;ecrireBool
call ecrbool


;aLaLigne
call ligsuiv


;ecrireChaine "Continue programme ? "
.DATA
mess2 DB "Continue programme ? $"
.CODE
lea dx, mess2
push dx
call ecrch


;lireEnt -8
lea dx,[bp-8]
push dx
call lirent


;goto FAIRE1
jmp FAIRE1


FAIT1:


;queue
nop
EXITCODE
End debut
