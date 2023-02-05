package com.example.ilabankdemo.utils

import com.example.ilabankdemo.R
import com.example.ilabankdemo.models.ItemData
import com.example.ilabankdemo.models.ViewPagerData


val itemDataList = arrayListOf(
    ViewPagerData(
       R.drawable.india,
        arrayListOf(
            ItemData(name = "Virat Kohli"),
            ItemData(name = "Rishab Pant"),
            ItemData(name = "Rohit Sharma"),
            ItemData(name = "K.L Rahul"),
            ItemData(name = "R.Ashwin"),
            ItemData(name = "Rabindra Jadega"),
            ItemData(name = "Shreyas Iyer"),
            ItemData(name = "Ajinkya Rahane"),
            ItemData(name = "Suryakumar Yadav"),
            ItemData(name = "Jasprit Bumrah")
        )
    ),
    ViewPagerData(
       R.drawable.aus,
        arrayListOf(
            ItemData(name = "Pat Cummins"),
            ItemData(name = "Ashton Agar"),
            ItemData(name = "Scott Boland"),
            ItemData(name = "Alex Carey"),
            ItemData(name = "Usman Khawaja"),
            ItemData(name = "Cameron Green"),
            ItemData(name = "SLance Morris"),
            ItemData(name = "Ajinkya Rahane"),
            ItemData(name = "Steve Smith"),
            ItemData(name = "Josh Hazlewood")
        )
    ),
    ViewPagerData(
       R.drawable.nz,
        arrayListOf(
            ItemData(name = "Michael Bracewell"),
            ItemData(name = "Henry Nicholls"),
            ItemData(name = "Scott Boland"),
            ItemData(name = "Glenn Phillips"),
            ItemData(name = "Adam Milne"),
            ItemData(name = "Jacob Duffy"),
            ItemData(name = "Daryl Mitchell"),
            ItemData(name = "Lockie Ferguson"),
            ItemData(name = "Finn Allen"),
            ItemData(name = "Josh Hazlewood")
        )
    )
)