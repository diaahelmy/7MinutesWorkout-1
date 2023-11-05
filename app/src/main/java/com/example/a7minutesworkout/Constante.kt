package com.example.a7minutesworkout

object Constante {
    fun defoltexercies(): ArrayList<exercises> {
        val list = ArrayList<exercises>()
        val jumping_jacks = exercises(
            1,
            "jumping jacks",
            R.drawable.guy_doing_jumping_jacks_home_workout_exercise_diagram_athletic_man_star_jumps_fitness_ill__1_,
            false,
            false,
        )
        list.add(jumping_jacks)
        val art_illustration = exercises(
            2,
            "art illustration",
            R.drawable.plank,
            false,
            false,

        )
        list.add(art_illustration)

        val full_Plank = exercises(
            3,
            " full Plank",
            R.drawable.full_plank,
            false,
            false,
        )
        list.add(full_Plank)

        val bench_triceps = exercises(
            4,
            "bench triceps",
            R.drawable.dips,
            false,
            false,

        )
        list.add(bench_triceps)
        val bicycle_crunch_illustr = exercises(
            5,
            "bicycle crunch illustr",
            R.drawable.man_doing_abdominal,
            false,
            false,

        )
        list.add(bicycle_crunch_illustr)
        val bird_dog_exercise = exercises(
            6,
            "bird dog exercise",
            R.drawable.bird_dog,
            false,
            false,

        )
        list.add(bird_dog_exercise)
        val jpush_up = exercises(
            7,
            "push up",
            R.drawable.push,
            false,
            false,

            )
        list.add(jpush_up)
        val single_leg_glute_bridge_arm = exercises(
            8,
            "single leg glute bridge arm",
            R.drawable.man_doing_single_leg_glute_bridge_arm_,
            false,
            false,


            )
        list.add(single_leg_glute_bridge_arm)

        val a = exercises(
            9,
            " a",
            R.drawable.basic_rgb,
            false,
            false,

            )
        list.add(a)

        val b = exercises(
            10,
            "b",
            R.drawable.art_illustration_201551861,
            false,
            false,

            )
        list.add(b)
        val c = exercises(
            11,
            "c",
            R.drawable.untitled,
            false,
            false,


            )
        list.add(c)
        val d = exercises(
            12,
            "d",
            R.drawable.d,
            false,
            false,

            )
        list.add(d)


        return list
    }


}