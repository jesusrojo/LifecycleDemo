package com.jesusrojo.lifecycledemo.presentation.activities

import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.jesusrojo.lifecycledemo.R
import com.jesusrojo.lifecycledemo.databinding.MainActivityLayoutBinding
import com.jesusrojo.lifecycledemo.presentation.dialogfragment.DetailsDialogFragment
import com.jesusrojo.lifecycledemo.presentation.fragments.MainFragment
import com.jesusrojo.lifecycledemo.presentation.fragments.ThirdFragment


class MainActivity : BaseActivity() {

    companion object { var count = 0 }
    private lateinit var binding: MainActivityLayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        count++
        lt("onCreate MainActivity $count")

        binding = MainActivityLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupNavController(binding)
    }

    private fun setupNavController(binding: MainActivityLayoutBinding) {

        setSupportActionBar(findViewById(R.id.toolbar))

        val navHostFragment: NavHostFragment =   supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController: NavController = navHostFragment.navController

        val appBarConfiguration =
            AppBarConfiguration.Builder(R.id.main_fragment_id)
            .setOpenableLayout(binding.drawerLayout)
            .build()

        setupActionBarWithNavController(navController, appBarConfiguration)
        binding.toolbar.setupWithNavController(navController, appBarConfiguration)
        binding.navView.setupWithNavController(navController)
    }

    private fun navigateToThirdFragment() {
        val navHostFragment: NavHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment

        val navController: NavController = navHostFragment.navController

        navController.navigate(R.id.action_MainFragment_to_ThirdFragment, null)
    }


    //MENU TOP RIGHT - THIRD FRAGMENT - SECOND FRAGMENT - MAIN FRAGMENT
    fun setupMenuOptionsSecondFragment() = binding.toolbar.menu.clear()

    fun setupMenuOptionsMainFragment() {
        binding.toolbar.menu.clear()

        binding.toolbar.inflateMenu(R.menu.menu_top_right)

        binding.toolbar.setOnMenuItemClickListener(Toolbar.OnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.menu_item_1 -> {
                    clearTextViewLogInMainFragment()
                    return@OnMenuItemClickListener true
                }
                R.id.menu_item_2 -> {
                    showDetailsDialogFragment()
                    return@OnMenuItemClickListener true
                }
                R.id.menu_item_3 -> {
                    navigateToThirdFragment()
                    return@OnMenuItemClickListener true
                }
            }
            return@OnMenuItemClickListener false
        })
    }

    fun setupMenuOptionsThirdFragment() {
        binding.toolbar.menu.clear()

        binding.toolbar.inflateMenu(R.menu.menu_top_right_third)

        binding.toolbar.setOnMenuItemClickListener(Toolbar.OnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.menu_item_1 -> {
                    onClickMenuTopRightThirdFragmentItem1()
                    return@OnMenuItemClickListener true
                }
            }
            return@OnMenuItemClickListener false
        })
    }

    // TO THIRD FRAGMENT
    private fun onClickMenuTopRightThirdFragmentItem1() {
        val navHostFragment: NavHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment

        val currentFragment = navHostFragment.childFragmentManager.fragments[0]

        val thirdFragment: ThirdFragment = currentFragment as ThirdFragment

        thirdFragment.onClickMenuTopRightThirdFragmentItem1()
    }

    // TO MAIN FRAGMENT
    private fun clearTextViewLogInMainFragment() {
        val navHostFragment: NavHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment

        val currentFragment = navHostFragment.childFragmentManager.fragments[0]

        val mainFragment: MainFragment = currentFragment as MainFragment

        mainFragment.clearTextViewLog()
    }

    private fun showDetailsDialogFragment() {
        l("showDetailsDialogFragment")
        DetailsDialogFragment.showDetailsDialogFragment(this,
            "avatar_URL", "details")
    }
}