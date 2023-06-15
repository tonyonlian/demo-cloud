
" leader: ,  快捷键前缀
let mapleader = ","

" 基础键位映射
" esc 使用jk
inoremap jk <esc>

" 映射idea常用快捷键
" 重命名
nnoremap <leader>r :action RenameElement<CR>
" 格式化当前文件
nnoremap <leader>f :action ReformatCode<CR><esc>
" quick java doc
nnoremap <leader>q :action QuickJavaDoc<CR>


" Vim 的插件
set surround
set multiple-cursors
set easymotion
set commentary


" 映射到idea快捷键
" 弹出输入框，可以跳到指定类
nnoremap <Space>gc :action GotoClass<CR>
" 弹出输入框，跳转到指定操作
nnoremap <Space>ga :action GotoAction<CR>
" 跳转到定义
nnoremap <Space>gd :action GotoDeclaration<CR>
" 跳转到实现
nnoremap <Space>gi :action GotoImplementation<CR>
" 跳转到指定的文件
nnoremap <Space>gf :action GotoFile<CR>
" 跳转到方法的声明
nnoremap <Space>gs :action GotoSuperMethod<CR>
" 跳转到测试
nnoremap <Space>gt :action GotoTest<CR>
" 跳转到变量的声明
nnoremap <Space>gS :action GotoSymbol<CR>

" 查找使用
nnoremap <Space>fu :action FindUsages<CR>
" 显示使用
nnoremap <Space>su :action ShowUsages<CR>

" 前进，相当似于eclipse中的alt+方向右键
nnoremap gf :action Forward<CR>
" 后退，相当于eclipse中的alt+方向左键
nnoremap gb :action Back<CR>

" gh=go head, 映射vim中的^
nnoremap gh ^
" gl=go last，映射vim中的$
nnoremap gl $

" Window operation
nnoremap <Space>ww <C-W>w
nnoremap <Space>wc <C-W>c
nnoremap <Space>wj <C-W>j
nnoremap <Space>wk <C-W>k
nnoremap <Space>wh <C-W>h
nnoremap <Space>wl <C-W>l
nnoremap <Space>ws <C-W>s
nnoremap <Space>w- <C-W>-
nnoremap <Space>w+ <C-W>+
nnoremap <Space>w= <C-W>=

nnoremap <Space>wv <C-W>vf

set relativenumber number
set ignorecase smartcase

set keep-english-in-normal-and-restore-in-insert
