import tkinter as tk

root = tk.Tk()
root.title("Simple Calculator")

e = tk.Entry(root, width=35, borderwidth=5)
e.grid(row=0,column=0,columnspan=3,padx=10,pady=10)

#Operation functions

def button_click(number):
    current = e.get()
    e.delete(0, tk.END)
    e.insert(0, str(current) + str(number)) 

def button_del():
    last = 0
    e.delete(0, tk.END)

def button_add():
    global last
    last = int(e.get())
    
    global Flag
    Flag = 1
    e.delete(0, tk.END)

def button_subi():
    global last
    last = int(e.get())
    
    global Flag
    Flag = 2
    e.delete(0, tk.END)

def button_multi():
    global last
    last = int(e.get())
    
    global Flag
    Flag = 3
    e.delete(0, tk.END)

def button_divi():
    global last
    last = int(e.get())
    
    global Flag
    Flag = 4
    e.delete(0, tk.END)

def button_modi():
    global last
    last = int(e.get())
    
    global Flag
    Flag = 5
    e.delete(0, tk.END)

def button_equals():
    current = int(e.get())
    e.delete(0, tk.END)        

    if Flag == 1:
        e.insert(0, last + current)
    elif Flag == 2:
        e.insert(0, last - current)
    elif Flag == 3:
        e.insert(0, last * current)
    elif Flag == 4:
        e.insert(0, last / current)
    elif Flag == 5:
        e.insert(0, last % current)
    else:
        e.insert(0, current)
    
    



#Define Buttons

button_1 = tk.Button(root, text='1',padx=40,pady=20, command=lambda: button_click(1))
button_2 = tk.Button(root, text='2',padx=40,pady=20, command=lambda: button_click(2))
button_3 = tk.Button(root, text='3',padx=40,pady=20, command=lambda: button_click(3))
button_4 = tk.Button(root, text='4',padx=40,pady=20, command=lambda: button_click(4))
button_5 = tk.Button(root, text='5',padx=40,pady=20, command=lambda: button_click(5))
button_6 = tk.Button(root, text='6',padx=40,pady=20, command=lambda: button_click(6))
button_7 = tk.Button(root, text='7',padx=40,pady=20, command=lambda: button_click(7))
button_8 = tk.Button(root, text='8',padx=40,pady=20, command=lambda: button_click(8))
button_9 = tk.Button(root, text='9',padx=40,pady=20, command=lambda: button_click(9))
button_0 = tk.Button(root, text='0',padx=40,pady=20, command=lambda: button_click(0))
button_addi = tk.Button(root, text='+',padx=39,pady=20, command=button_add)
button_equal = tk.Button(root, text='=',padx=53,pady=20, command=button_equals)
button_sub = tk.Button(root, text='-',padx=40,pady=20, command=button_subi)
button_mult = tk.Button(root, text='*',padx=53,pady=20, command=button_multi)
button_div = tk.Button(root, text='/',padx=53,pady=20, command=button_divi)
button_clear = tk.Button(root, text='C',padx=53,pady=20, command=button_del)
button_mod = tk.Button(root, text='%',padx=51,pady=20, command=button_modi)


# Place buttons

button_7.grid(row=1,column=0)
button_8.grid(row=1,column=1)
button_9.grid(row=1,column=2)

button_4.grid(row=2,column=0)
button_5.grid(row=2,column=1)
button_6.grid(row=2,column=2)

button_1.grid(row=3,column=0)
button_2.grid(row=3,column=1)
button_3.grid(row=3,column=2)

button_0.grid(row=4, column=1)

button_clear.grid(row=0, column=3)
button_mult.grid(row=1, column=3)
button_div.grid(row=2, column=3)
button_mod.grid(row=3, column=3)
button_addi.grid(row=4, column=0)
button_sub.grid(row=4, column=2)
button_equal.grid(row=4, column=3)


root.mainloop()