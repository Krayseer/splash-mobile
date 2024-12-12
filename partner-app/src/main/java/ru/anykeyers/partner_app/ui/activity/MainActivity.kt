package ru.anykeyers.partner_app.ui.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import ru.anykeyers.partner_app.R
import ru.anykeyers.partner_app.ui.fragment.AnalyticsFragment
import ru.anykeyers.partner_app.ui.fragment.account.AccountFragment
import ru.anykeyers.partner_app.ui.fragment.HomeFragment
import ru.anykeyers.partner_app.ui.fragment.account.notification.NotificationFragment
import ru.anykeyers.partner_app.ui.fragment.order.OrderFragment
import ru.anykeyers.partner_app.ui.fragment.configuration.ConfigurationFragment

/**
 * Главный Activity приложения
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        loadFragment(HomeFragment())

        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    loadFragment(HomeFragment())
                    true
                }
                R.id.nav_service_and_boxes -> {
                    loadFragment(ConfigurationFragment())
                    true
                }
                R.id.nav_orders -> {
                    loadFragment(OrderFragment())
                    true
                }
                R.id.nav_analytics -> {
                    loadFragment(AnalyticsFragment())
                    true
                }
                R.id.nav_menu -> {
                    loadFragment(AccountFragment())
                    true
                }
                else -> false
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_chats -> {
                Toast.makeText(this, "Чаты", Toast.LENGTH_SHORT).show()
                return true
            }
            R.id.action_notifications -> {
                loadFragment(NotificationFragment())
                return true
            }
            R.id.action_login -> {
                loadFragment(AccountFragment())
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack()
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }

}