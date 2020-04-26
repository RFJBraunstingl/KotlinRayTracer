package dev.rfj.play

fun tick(env: Environment, projectile: Projectile): Projectile {

    val newPosition = projectile.position.plus(projectile.velocity)
    val newVelocity = projectile.velocity.plus(env.gravity).plus(env.wind)

    return Projectile(newPosition.asPoint(), newVelocity.asVector())
}