;entete
extrn lirent:proc, ecrent:proc
extrn ecrbool:proc
extrn ecrch:proc, ligsuiv:proc
.model SMALL
.586

.CODE



debut:
STARTUPCODE

main:

;ouvbloc 10
enter 10,0

;iconst 2
push word ptr 2


;iconst 2
push word ptr 2


;iconst 3
push word ptr 3


;imul
pop bx 
pop ax 
imul bx
push ax


;iadd
pop bx 
pop ax 
add ax,bx
push ax


;istore -10
pop ax 
mov word ptr [bp-10], ax


;iload -10
push word ptr [bp-10]


;ecrireEnt
call ecrent


;aLaLigne
call ligsuiv


;iconst 2
push word ptr 2


;iconst 2
push word ptr 2


;iconst 2
push word ptr 2


;iconst 1
push word ptr 1


;iadd
pop bx 
pop ax 
add ax,bx
push ax


;iconst 0
push word ptr 0


;iadd
pop bx 
pop ax 
add ax,bx
push ax


;imul
pop bx 
pop ax 
imul bx
push ax


;iadd
pop bx 
pop ax 
add ax,bx
push ax


;queue
nop
EXITCODE
end debut
