package com.skydoves.pokedex.upgrader.ui.dashboard

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.skydoves.pokedex.R
import com.skydoves.pokedex.upgrader.ui.dashboard.about.AboutFragment
import com.skydoves.pokedex.upgrader.ui.dashboard.evolution.EvolutionFragment
import com.skydoves.pokedex.upgrader.ui.dashboard.moves.MovesFragment
import com.skydoves.pokedex.upgrader.ui.dashboard.stats.StatsFragment

class ViewPagerAdapter(
    supportFragmentManager: FragmentManager,
    context: Context,
    private val pokemonId: String
) : FragmentStatePagerAdapter(supportFragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    data class Page(val title: String, val ctor: () -> Fragment)

    @Suppress("MoveLambdaOutsideParentheses")
    private val pages = listOf(
        Page(
            context.getString(R.string.dashboard_tab_1),
            { AboutFragment.newInstance(pokemonId) }
        ),
        Page(
            context.getString(R.string.dashboard_tab_2),
            { StatsFragment.newInstance(pokemonId) }
        ),
        Page(
            context.getString(R.string.dashboard_tab_3),
            { EvolutionFragment.newInstance(pokemonId) }
        ),
        Page(
            context.getString(R.string.dashboard_tab_4),
            { MovesFragment() }
        )
    )

    override fun getItem(position: Int): Fragment {
        return pages[position].ctor()
    }

    override fun getCount(): Int {
        return pages.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return pages[position].title
    }
}
